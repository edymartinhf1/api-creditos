package com.bootcamp.bank.creditos.service.impl;


import com.bootcamp.bank.creditos.clients.ClientApiConsumos;
import com.bootcamp.bank.creditos.clients.ClientApiPagos;
import com.bootcamp.bank.creditos.model.CreditoProductoInfo;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import com.bootcamp.bank.creditos.service.CreditoDeudasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class CreditoDeudasServiceImpl implements CreditoDeudasService {

    private final CreditoProductoRepository creditoProductoRepository;

    private final ClientApiConsumos clientApiConsumos;

    private final ClientApiPagos clientApiPagos;

    @Override
    public Flux<CreditoProductoInfo> getCreditosConDeudaPorIdCliente(String idCliente) {

        return creditoProductoRepository.findByIdCliente(idCliente)
                .flatMap(creditoProductoDao -> {
                    log.info(" credito producto "+creditoProductoDao.toString());
                    return Mono.zip(getConsumnos(creditoProductoDao.getNumeroCredito()), getPagos(creditoProductoDao.getNumeroCredito()), (consumos,pagos)->{
                        CreditoProductoInfo creditoProductoInfo= new CreditoProductoInfo();
                        creditoProductoInfo.setIdCliente(creditoProductoDao.getIdCliente());
                        creditoProductoInfo.setNumeroCredito(creditoProductoDao.getNumeroCredito());
                        creditoProductoInfo.setNumeroTarjetaCredito(creditoProductoDao.getNumeroTarjetaCredito());
                        creditoProductoInfo.setLineaCredito(creditoProductoDao.getLineaCredito());
                        creditoProductoInfo.setFechaCreacion(creditoProductoDao.getFechaCreacion());
                        creditoProductoInfo.setDiaCierreMes(creditoProductoDao.getDiaCierreMes());
                        creditoProductoInfo.setPagos(pagos);
                        creditoProductoInfo.setConsumos(consumos);
                        creditoProductoInfo.setSaldo(creditoProductoDao.getLineaCredito()+pagos+(consumos*-1));
                        return  creditoProductoInfo;
                    });
                });

    }


    /**
     * Permite calcular el total de consumos por numero de credito
     * @param numeroCredito
     * @return
     */
    public Mono<Double> getConsumnos(String numeroCredito) {
        return clientApiConsumos
                .getConsumosNumeroCredito(numeroCredito)
                .reduce(0.00,(acum,e)->acum+e.getImporte());
    }

    /**
     * Permite obtener el total de pagos por numero de credito
     * @param numeroCredito
     * @return
     */
    public Mono<Double> getPagos(String numeroCredito) {
        return clientApiPagos
                .getPagosNumeroCredito(numeroCredito)
                .reduce(0.00,(acum,e)->acum+e.getImporte());
    }





}

package com.bootcamp.bank.creditos.service.impl;

import com.bootcamp.bank.creditos.clients.ClientApiClientes;
import com.bootcamp.bank.creditos.exception.BusinessException;
import com.bootcamp.bank.creditos.factory.CreditoA;
import com.bootcamp.bank.creditos.factory.FactoryCreditos;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import com.bootcamp.bank.creditos.service.CreditoServiceI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoServiceI {

    private final CreditoProductoRepository creditoProductoRepository;

    private final ClientApiClientes clientApiClientes;

    @Value("${tipo.credito.personal}")  String tipoCreditoPersonal;

    /**
     * Generacion de creditos
     * @param creditoProductoDao
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> save(CreditoProductoDao creditoProductoDao) {
        FactoryCreditos factoryCreditos=new FactoryCreditos();
        CreditoA creditoproduct = factoryCreditos.getProductoCredito(creditoProductoDao.getTipoCredito());
        creditoproduct.setIdCliente(creditoProductoDao.getIdCliente());
        BeanUtils.copyProperties(creditoproduct,creditoProductoDao);
        log.info("credito tipo "+creditoProductoDao.getTipoCredito());
        if (creditoProductoDao.getTipoCredito().equals(tipoCreditoPersonal)){
            log.info("credito personal - id cliente :"+creditoProductoDao.getIdCliente());

            CreditoProductoDao finalCreditoProductoDao = creditoProductoDao;
            return clientApiClientes.getClientes(creditoProductoDao.getIdCliente())
                    .switchIfEmpty(Mono.error(()->new BusinessException("No existe cliente con el id "+finalCreditoProductoDao.getIdCliente())))
                    .flatMap(c -> {
                        return creditoProductoRepository.findByIdCliente(finalCreditoProductoDao.getIdCliente())
                                .next()
                                .switchIfEmpty(creditoProductoRepository.save(finalCreditoProductoDao));

                    });

        } else {
            log.info("credito empresarial - id cliente :"+creditoProductoDao.getIdCliente());

            CreditoProductoDao finalCreditoProductoDao = creditoProductoDao;
            return clientApiClientes.getClientes(creditoProductoDao.getIdCliente())
                    .switchIfEmpty(Mono.error(()->new BusinessException("No existe cliente con el id "+finalCreditoProductoDao.getIdCliente()))
                    ).flatMap(c->{
                        log.info("se encontro cliente "+c.getId()+" nombre:"+c.getNombre());
                        return creditoProductoRepository.save(finalCreditoProductoDao);
                    });
        }
    }

    @Override
    public Flux<CreditoProductoDao> findAll() {
        return creditoProductoRepository.findAll();
    }

    /**
     * Permite buscar un credito por id
     * @param id
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> findById(String id) {
        return creditoProductoRepository.findById(id);
    }

    @Override
    public Flux<CreditoProductoDao> findByIdCliente(String idCliente) {
        return creditoProductoRepository.findByIdCliente(idCliente);
    }

    @Override
    public Flux<CreditoProductoDao> findByIdClienteAndTipoCredito(String idCliente, String tipoCredito) {
        return creditoProductoRepository.findByIdClienteAndTipoCredito(idCliente,tipoCredito);
    }

    /**
     * Permite actualizar un credito producto
     * @param creditoProductoDao
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> update(CreditoProductoDao creditoProductoDao) {
        return creditoProductoRepository.save(creditoProductoDao);
    }
    /**
     * Permite eliminar un producto credito por id
     * @param id
     * @return
     */
    @Override
    public Mono<Void> delete(String id) {
        return creditoProductoRepository.deleteById(id);
    }
}

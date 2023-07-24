package com.bootcamp.bank.creditos.strategy.creditos;

import com.bootcamp.bank.creditos.exception.BusinessException;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import com.bootcamp.bank.creditos.service.CreditoDeudasService;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
public class CreditoPersonalStrategy implements CreditoProductoStrategy{
    /**
     * Restriccion personal: solo se permite un solo crédito por persona.
     * Se valida que no contenga productos de credito con deuda - entregable 3
     * @param creditoProductoRepository
     * @param creditoProductoDao
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> registrarCredito(
            CreditoProductoRepository creditoProductoRepository,
            CreditoDeudasService creditoDeudasService,
            CreditoProductoDao creditoProductoDao) {

        return creditoDeudasService.getCreditosConDeudaPorIdCliente(creditoProductoDao.getIdCliente())
                .collectList()
                .flatMap(listDeudas -> {
                    if (!listDeudas.isEmpty()) {
                        log.info(" contiene productos de credito con deuda ");
                        return Mono.error(() -> new BusinessException(" contiene productos de credito con deuda "));
                    }
                    return creditoProductoRepository.findByIdClienteAndTipoCredito(creditoProductoDao.getIdCliente(), "PER")
                            .collectList()
                            .flatMap(listaCreditos -> listaCreditos.isEmpty() ? creditoProductoRepository.save(creditoProductoDao) : Mono.error(() -> new BusinessException("Eli cliente ya posee un credito personal ")));
                });
    }
}

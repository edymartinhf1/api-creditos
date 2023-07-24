package com.bootcamp.bank.creditos.strategy.creditos;

import com.bootcamp.bank.creditos.exception.BusinessException;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import com.bootcamp.bank.creditos.service.CreditoDeudasService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
@Log4j2
public class CreditoTarjetaCreditoStrategy implements CreditoProductoStrategy{
    @Override
    public Mono<CreditoProductoDao> registrarCredito(
            CreditoProductoRepository creditoProductoRepository ,
            CreditoDeudasService creditoDeudasService,
            CreditoProductoDao creditoProductoDao) {

        return creditoDeudasService.getCreditosConDeudaPorIdCliente(creditoProductoDao.getIdCliente())
                .collectList()
                .flatMap(listDeudas -> {
                    if (!listDeudas.isEmpty()) {
                        log.info(" contiene productos de credito con deuda - id cliente");
                        return Mono.error(() -> new BusinessException(" contiene productos de credito con deuda, no puede generarse producto de credito "));
                    }
                    return creditoProductoRepository.save(creditoProductoDao);
                });

    }
}

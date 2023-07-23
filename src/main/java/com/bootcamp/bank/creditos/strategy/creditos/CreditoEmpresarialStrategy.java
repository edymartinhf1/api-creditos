package com.bootcamp.bank.creditos.strategy.creditos;

import com.bootcamp.bank.creditos.exception.BusinessException;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import com.bootcamp.bank.creditos.service.CreditoDeudasService;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
public class CreditoEmpresarialStrategy implements CreditoProductoStrategy{
    /**
     * Empresarial: se permite más de un crédito por empresa.
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
                .flatMap(listDeudas-> {
                    if (!listDeudas.isEmpty()) {
                        log.info(" contiene productos de credito con deuda ");
                        return Mono.error(()->new BusinessException(" contiene productos de credito con deuda "));
                    }
                    return creditoProductoRepository.save(creditoProductoDao);
                });
    }
}

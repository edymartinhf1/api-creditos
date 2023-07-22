package com.bootcamp.bank.creditos.strategy.creditos;

import com.bootcamp.bank.creditos.exception.BusinessException;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import reactor.core.publisher.Mono;

public class CreditoPersonalStrategy implements CreditoProductoStrategy{
    /**
     * Restriccion personal: solo se permite un solo crédito por persona.
     * @param creditoProductoRepository
     * @param creditoProductoDao
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> registrarCredito(CreditoProductoRepository creditoProductoRepository,CreditoProductoDao creditoProductoDao) {
        return creditoProductoRepository.findByIdClienteAndTipoCredito(creditoProductoDao.getIdCliente(),"PER")
                .collectList()
                .flatMap(listaCreditos->{
                    return listaCreditos.isEmpty() ? creditoProductoRepository.save(creditoProductoDao) : Mono.error(()->new BusinessException("Eli cliente ya posee un credito personal "));
                });

    }
}

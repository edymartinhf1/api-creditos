package com.bootcamp.bank.creditos.strategy.creditos;

import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import reactor.core.publisher.Mono;

public class CreditoTarjetaCreditoStrategy implements CreditoProductoStrategy{
    @Override
    public Mono<CreditoProductoDao> registrarCredito(CreditoProductoRepository creditoProductoRepository ,CreditoProductoDao creditoProductoDao) {
        return creditoProductoRepository.save(creditoProductoDao);
    }
}

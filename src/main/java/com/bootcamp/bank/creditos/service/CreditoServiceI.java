package com.bootcamp.bank.creditos.service;

import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditoServiceI {
    Mono<CreditoProductoDao> save(CreditoProductoDao creditoProductoDao);
    Flux<CreditoProductoDao> findAll();
    Mono<CreditoProductoDao> findById(String id);
    Flux<CreditoProductoDao> findByIdCliente(String idCliente);
    Flux<CreditoProductoDao> findByIdClienteAndTipoCredito(String idCliente, String tipoCredito);
    Mono<CreditoProductoDao> update(CreditoProductoDao creditoProductoDao);
    Mono<Void> delete(String id);


}

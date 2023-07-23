package com.bootcamp.bank.creditos.service;

import com.bootcamp.bank.creditos.model.CreditoProducto;
import com.bootcamp.bank.creditos.model.CreditoProductoInfo;
import reactor.core.publisher.Flux;

public interface CreditoDeudasService {
    Flux<CreditoProductoInfo> getCreditosConDeudaPorIdCliente(String idCliente);
}

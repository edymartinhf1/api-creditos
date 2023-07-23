package com.bootcamp.bank.creditos.controller;

import com.bootcamp.bank.creditos.model.CreditoProductoInfo;
import com.bootcamp.bank.creditos.service.CreditoDeudasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Clase Creditos con informacion de saldos y deuda
 */
@RestController
@RequestMapping("/creditos/deudas")
@RequiredArgsConstructor
public class CreditosDeudasController {

    private final CreditoDeudasService creditoDeudasService;

    /**
     * Permite obtener lista de creditos con deuda por Id cliente
     * Entregable 3
     * @param idCliente
     * @return
     */
    @GetMapping("/cliente/{idCliente}")
    public Flux<CreditoProductoInfo> getCreditosConDeudaPorIdCliente(@PathVariable String idCliente){
        return creditoDeudasService.getCreditosConDeudaPorIdCliente(idCliente);
    }

}

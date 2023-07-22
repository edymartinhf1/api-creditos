package com.bootcamp.bank.creditos.controller;

import com.bootcamp.bank.creditos.model.CreditoProductoInfo;
import com.bootcamp.bank.creditos.service.CreditoDeudasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/creditos/deuda")
@RequiredArgsConstructor
public class CreditosDeudasController {

    private final CreditoDeudasService creditoDeudasService;

    /**
     * Permite obtener creditos Con info de deuda por Id cliente
     * Entregable 3
     * @param idCliente
     * @return
     */
    @GetMapping("/{idCliente}")
    public Flux<CreditoProductoInfo> getCreditDebtPorIdCliente(@PathVariable String idCliente){
        return creditoDeudasService.getCreditDebtPorIdCliente(idCliente);
    }

}

package com.bootcamp.bank.creditos.controller;

import com.bootcamp.bank.creditos.model.CreditoProducto;
import com.bootcamp.bank.creditos.model.CreditoProductoPost;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.service.CreditoServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase Controller gestion de creditos
 */
@RestController
@RequestMapping("/creditos")
@RequiredArgsConstructor
public class CreditosController {

    private final CreditoServiceI creditoServiceI;

    /**
     * Permite crear producto de credito
     * @param creditoProductoPost
     * @return
     */
    @PostMapping
    public Mono<CreditoProducto> createCreditProduct(@RequestBody CreditoProductoPost creditoProductoPost) {
        return creditoServiceI.save(this.fromCreditoProductoPostToCreditoProductoDao(creditoProductoPost))
                .map(this::fromCreditoProductoDaoToCreditoProductoDto);
    }

    /**
     * Permite Obtener todos los productos de credito
     * @return
     */
    @GetMapping
    public Flux<CreditoProducto> getAll(){
        return creditoServiceI.findAll().map(this::fromCreditoProductoDaoToCreditoProductoDto);
    }

    /**
     * Permite obtener id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<CreditoProducto> findById(@PathVariable String id) {
        return creditoServiceI.findById(id)
                .map(this::fromCreditoProductoDaoToCreditoProductoDto);
    }


    /**
     * Permite obtener productos de credito por id cliente
     * @param idCliente
     * @return
     */
    @GetMapping("/cliente/{idCliente}")
    public Flux<CreditoProducto> findClienteByIdCliente(@PathVariable String idCliente) {
        return creditoServiceI.findByIdCliente(idCliente)
                .map(this::fromCreditoProductoDaoToCreditoProductoDto);
    }

    @GetMapping("/client/{idCliente}/tipo/{tipoCredito}")
    public Flux<CreditoProducto> findClienteByIdClienteAndTipoCredito(@PathVariable String idCliente,@PathVariable String tipoCredito) {
        return creditoServiceI.findByIdClienteAndTipoCredito(idCliente,tipoCredito)
                .map(this::fromCreditoProductoDaoToCreditoProductoDto);
    }


    /**
     * Permite actualizar informacion de producto de creditos
     * @param creditoProductoPost
     * @return
     */
    @PutMapping
    public Mono<CreditoProducto> updateCreditProduct(@RequestBody CreditoProductoPost creditoProductoPost) {
        return creditoServiceI.update(this.fromCreditoProductoPostToCreditoProductoDao(creditoProductoPost))
                .map(this::fromCreditoProductoDaoToCreditoProductoDto);
    }

    /**
     * Permite eliminar un producto de credito
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteCreditProduct(@PathVariable String id) {
        return creditoServiceI.delete(id);
    }


    private CreditoProducto fromCreditoProductoDaoToCreditoProductoDto(CreditoProductoDao creditoProductoDao) {
        CreditoProducto creditoProducto = new CreditoProducto();
        BeanUtils.copyProperties(creditoProductoDao,creditoProducto);
        return creditoProducto;
    }

    private CreditoProductoDao fromCreditoProductoPostToCreditoProductoDao(CreditoProductoPost creditoProductoPost) {
        CreditoProductoDao creditoProductoDao = new CreditoProductoDao();
        BeanUtils.copyProperties(creditoProductoPost,creditoProductoDao);
        return creditoProductoDao;
    }

}

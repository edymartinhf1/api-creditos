package com.bootcamp.bank.creditos.factory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class CreditoA {
    private String id;
    private String idCliente;
    private String estado;
    private String tipoCredito; // PER = personal , EMP = empresarial , TJC = tarjeta de credito
    private String  numeroCredito;
    private String  numeroTarjetaCredito;
    private String fechaCreacionT;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaPagoVencimiento;
    private Double  lineaCredito;

    protected CreditoA() {
        this.configurarProducto();
    }

    abstract void configurarProducto();
}

package com.bootcamp.bank.creditos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreditoProducto {
    private String id;
    private String  idCliente;
    private String  tipoCredito; // PER = personal , EMP = empresarial , TJC = tarjeta de credito
    private String  numeroCredito;
    private String  numeroTarjetaCredito;
    private LocalDateTime fechaCreacion;
    private String  fechaCreacionT;
    private Double  lineaCredito;
    private String diaCierreMes;
    private Boolean flgDeuda;
    private String codigo;

}
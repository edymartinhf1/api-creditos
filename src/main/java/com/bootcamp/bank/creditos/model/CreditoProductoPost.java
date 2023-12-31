package com.bootcamp.bank.creditos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreditoProductoPost {
    private String  idCliente;
    private String  tipoCredito; // PER = personal , EMP = empresarial , TJC = tarjeta de credito
    private String  numeroCredito;
    private String  numeroTarjetaCredito;
    private LocalDateTime fechaCreacion;
    private Double  lineaCredito;
    private String  diaCierreMes;


}
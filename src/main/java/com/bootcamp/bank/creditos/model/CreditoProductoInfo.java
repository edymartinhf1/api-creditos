package com.bootcamp.bank.creditos.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreditoProductoInfo {
    private String  idCliente;
    private String  tipoCredito;
    private String  numeroCredito;
    private String  numeroTarjetaCredito;
    private LocalDateTime fechaCreacion;
    private Double  lineaCredito;
    private Double pagos;
    private Double consumos;
    private Double saldo;

}

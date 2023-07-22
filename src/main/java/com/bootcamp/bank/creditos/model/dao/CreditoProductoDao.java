package com.bootcamp.bank.creditos.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("creditosproductos")
public class CreditoProductoDao {
    @Id
    private String id;
    private String idCliente;
    private String estado;
    private String tipoCredito; // PER = personal , EMP = empresarial , TJC = tarjeta de credito
    private String numeroCredito;
    private String numeroTarjetaCredito;
    private LocalDateTime fechaCreacion;
    private String fechaCreacionT;
    private LocalDateTime fechaPagoVencimiento;
    private Double lineaCredito;



}
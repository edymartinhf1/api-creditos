package com.bootcamp.bank.creditos.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("creditosproductos")
public class CreditoProductoDao {
    @Id
    private String id;
    private String idCliente;
    private String estado;
    private String tipoCredito; // PER = personal , EMP = empresarial , TJC = tarjeta de credito
    private String  numeroCredito;
    private String  numeroTarjeta;
    private String  fechaCreacion;
    private Double  lineaCredito;


}
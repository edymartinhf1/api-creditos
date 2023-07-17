package com.bootcamp.bank.creditos.factory;

import com.bootcamp.bank.creditos.utils.Util;

public class CreditoEmpresarial extends  CreditoA{
    @Override
    void configurarProducto() {
        this.setTipoCredito("EMP");
        int randomNumber = Util.generateRandomNumber(1, 100000);
        this.setNumeroCredito("EMP".concat(Integer.toString(randomNumber)));
        this.setFechaCreacion (Util.getCurrentDateAsString("dd/MM/yyyy"));
        this.setLineaCredito(50000.00); // linea de credito referencial
        this.setEstado("ACT");
    }
}

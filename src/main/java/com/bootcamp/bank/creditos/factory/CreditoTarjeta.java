package com.bootcamp.bank.creditos.factory;

import com.bootcamp.bank.creditos.utils.Util;

public class CreditoTarjeta extends CreditoA{
    @Override
    void configurarProducto() {
        int randomNumber = Util.generateRandomNumber(1, 100000);
        super.setTipoCredito("TJC");
        super.setNumeroCredito("TJC"+Integer.toString(randomNumber));
        super.setNumeroTarjetaCredito("TAR-"+Integer.toString(randomNumber));
        super.setFechaCreacion (Util.getCurrentLocalDate());
        super.setFechaCreacionT(Util.getCurrentDateAsString("dd/MM/yyyy"));
        super.setLineaCredito(25000.00); // linea de credito referencial
        super.setEstado("ACT");
    }
}

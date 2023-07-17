package com.bootcamp.bank.creditos.factory;

import org.springframework.stereotype.Component;

/**
 * Factory Creditos
 */
@Component
public class FactoryCreditos {

    public CreditoA getProductoCredito(String tipo){
        if (tipo.equals("PER")){
            return new CreditoPersonal();
        } else if (tipo.equals("EMP")){
            return new CreditoEmpresarial();
        } else if (tipo.equals("TJC")) {
            return new CreditoTarjeta();
        }
        return null;
    }
}

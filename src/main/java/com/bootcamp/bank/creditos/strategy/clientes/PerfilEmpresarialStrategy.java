package com.bootcamp.bank.creditos.strategy.clientes;


import com.bootcamp.bank.creditos.model.Cliente;
import com.bootcamp.bank.creditos.model.PerfilInfo;

import java.util.List;

public class PerfilEmpresarialStrategy implements PerfilClienteStrategy {

    @Override
    public PerfilInfo configurarPerfil(Cliente cliente) {
        PerfilInfo perfil= new PerfilInfo();
        if (cliente.getTipoCli().equals("EMP")){
            List<String> tiposCuentasPermitidas = List.of("CTE","PYM");
            perfil.setPerfiles(tiposCuentasPermitidas);
        }
        return perfil;
    }
}

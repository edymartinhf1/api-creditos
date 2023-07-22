package com.bootcamp.bank.creditos.strategy.clientes;



import com.bootcamp.bank.creditos.model.Cliente;
import com.bootcamp.bank.creditos.model.PerfilInfo;

import java.util.List;

public class PerfilPersonalSstrategy implements PerfilClienteStrategy {
    @Override
    public PerfilInfo configurarPerfil(Cliente cliente) {
        PerfilInfo perfil= new PerfilInfo();
        if (cliente.getTipoCli().equals("PER")){
            List<String> tiposCuentasPermitidas = List.of("AHO","CTE","PZF","VIP");
            perfil.setPerfiles(tiposCuentasPermitidas);
        }
        return perfil;
    }
}

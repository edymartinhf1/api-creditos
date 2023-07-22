package com.bootcamp.bank.creditos.strategy.clientes;


import com.bootcamp.bank.creditos.model.Cliente;
import com.bootcamp.bank.creditos.model.PerfilInfo;

public interface PerfilClienteStrategy {
    PerfilInfo configurarPerfil(Cliente cliente);
}

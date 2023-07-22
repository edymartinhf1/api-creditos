package com.bootcamp.bank.creditos.service.impl;

import com.bootcamp.bank.creditos.clients.ClientApiClientes;
import com.bootcamp.bank.creditos.factory.CreditoA;
import com.bootcamp.bank.creditos.factory.FactoryCreditos;
import com.bootcamp.bank.creditos.model.PerfilInfo;
import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import com.bootcamp.bank.creditos.model.enums.CreditosTipoTypes;
import com.bootcamp.bank.creditos.model.enums.PerfilClienteTypes;
import com.bootcamp.bank.creditos.service.CreditoServiceI;
import com.bootcamp.bank.creditos.strategy.clientes.PerfilClienteStrategy;
import com.bootcamp.bank.creditos.strategy.clientes.PerfilClienteStrategyFactory;
import com.bootcamp.bank.creditos.strategy.creditos.CreditoProductoStrategy;
import com.bootcamp.bank.creditos.strategy.creditos.CreditoProductoStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditoServiceImpl implements CreditoServiceI {

    private final CreditoProductoRepository creditoProductoRepository;

    private final ClientApiClientes clientApiClientes;

    private final CreditoProductoStrategyFactory creditoProductoStrategyFactory;

    private final PerfilClienteStrategyFactory perfilClienteStrategyFactory;

    @Value("${tipo.credito.personal}")  String tipoCreditoPersonal;

    /**
     * Generacion de creditos
     * @param creditoProductoDao
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> save(CreditoProductoDao creditoProductoDao) {

        return clientApiClientes.getClientes(creditoProductoDao.getIdCliente())
                .flatMap(cliente->{
                    FactoryCreditos factoryCreditos=new FactoryCreditos();
                    CreditoA creditoproduct = factoryCreditos.getProductoCredito(creditoProductoDao.getTipoCredito());
                    creditoproduct.setIdCliente(creditoProductoDao.getIdCliente());
                    BeanUtils.copyProperties(creditoproduct,creditoProductoDao);
                    log.info("credito tipo "+creditoProductoDao.getTipoCredito());


                    // Tipos de cuenta por tipo de cliente
                    PerfilClienteTypes perfilClienteTypes= setPerfilCliente.apply(cliente.getTipoCli());
                    PerfilClienteStrategy strategyPerfil = perfilClienteStrategyFactory.getStrategy(perfilClienteTypes);
                    PerfilInfo perfilInfo = strategyPerfil.configurarPerfil(cliente);

                    // Estrategias por tipo de credito
                    CreditosTipoTypes cred= setTipoCredito.apply(creditoProductoDao.getTipoCredito());
                    CreditoProductoStrategy strategy= creditoProductoStrategyFactory.getStrategy(cred);

                    return strategy.registrarCredito(creditoProductoRepository,creditoProductoDao);

                });





    }

    @Override
    public Flux<CreditoProductoDao> findAll() {
        return creditoProductoRepository.findAll();
    }

    /**
     * Permite buscar un credito por id
     * @param id
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> findById(String id) {
        return creditoProductoRepository.findById(id);
    }

    @Override
    public Flux<CreditoProductoDao> findByIdCliente(String idCliente) {
        return creditoProductoRepository.findByIdCliente(idCliente);
    }

    @Override
    public Flux<CreditoProductoDao> findByIdClienteAndTipoCredito(String idCliente, String tipoCredito) {
        return creditoProductoRepository.findByIdClienteAndTipoCredito(idCliente,tipoCredito);
    }

    /**
     * Permite actualizar un credito producto
     * @param creditoProductoDao
     * @return
     */
    @Override
    public Mono<CreditoProductoDao> update(CreditoProductoDao creditoProductoDao) {
        return clientApiClientes.getClientes(creditoProductoDao.getIdCliente())
                .flatMap(cliente->{
                    return creditoProductoRepository.save(creditoProductoDao);
                });


    }
    /**
     * Permite eliminar un producto credito por id
     * @param id
     * @return
     */
    @Override
    public Mono<Void> delete(String id) {
        return creditoProductoRepository.deleteById(id);
    }

    Function<String, CreditosTipoTypes> setTipoCredito = tipoCredito  -> {
        CreditosTipoTypes creditosTipoTypes= null;
        switch (tipoCredito) {
            case "PER" -> creditosTipoTypes= CreditosTipoTypes.PERSONAL;

            case "EMP" -> creditosTipoTypes= CreditosTipoTypes.EMPRESARIAL;

            case "TJC" -> creditosTipoTypes= CreditosTipoTypes.TARJETA_CREDITO;

            default -> creditosTipoTypes =CreditosTipoTypes.INVALIDO;

        }
        return creditosTipoTypes;
    };

    Function<String, PerfilClienteTypes> setPerfilCliente = perfilCliente  -> {
        PerfilClienteTypes perfilClienteTypes= null;
        switch (perfilCliente) {
            case "EMP" -> perfilClienteTypes= PerfilClienteTypes.EMPRESARIAL;

            case "PER" -> perfilClienteTypes= PerfilClienteTypes.PERSONAL;

            default -> perfilClienteTypes = PerfilClienteTypes.INVALIDO;

        }
        return perfilClienteTypes;
    };
}

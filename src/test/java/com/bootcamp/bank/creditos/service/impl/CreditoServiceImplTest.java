package com.bootcamp.bank.creditos.service.impl;

import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@Log4j2
class CreditoServiceImplTest {


    @Mock
    private CreditoProductoRepository creditoProductoRepository;

    @InjectMocks
    private CreditoServiceImpl creditoService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        CreditoProductoDao expected = new CreditoProductoDao();
        expected.setId("1");
        expected.setNumeroCredito("0001458");

        CreditoProductoDao credito = new CreditoProductoDao();
        credito.setId("1");
        credito.setNumeroCredito("0001458");


        Mockito.when( creditoProductoRepository.save(Mockito.any(CreditoProductoDao.class)) )
                .thenReturn( Mono.just(credito) );
        log.info("step 1"+credito.toString());

        CreditoProductoDao actualiza=new CreditoProductoDao();
        actualiza.setId("1");
        actualiza.setNumeroCredito("0001458");

        String numeroCredito="456-789-456";
        Mono<CreditoProductoDao> actual0 = creditoProductoRepository.save(actualiza);
        CreditoProductoDao actual=actual0.block();
        log.info("step 2"+actual.toString());

        Assertions.assertEquals(expected.getId(),actual.getId());
        Assertions.assertEquals(expected.getNumeroCredito(),actual.getNumeroCredito());
        Assertions.assertEquals(expected.getTipoCredito(),actual.getTipoCredito());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        CreditoProductoDao espero = new CreditoProductoDao();
        espero.setId("1");
        espero.setNumeroCredito("0001460");

        CreditoProductoDao credito = new CreditoProductoDao();
        credito.setId("1");
        credito.setNumeroCredito("0001460");

        Mockito.when( creditoProductoRepository.findById("1") )
                .thenReturn(Mono.just(credito));

        Mono<CreditoProductoDao> recibo1 = creditoProductoRepository.findById("1");
        CreditoProductoDao recibo = recibo1.block();

        Assertions.assertEquals(espero.getId(),recibo.getId());
        Assertions.assertEquals(espero.getNumeroCredito(),recibo.getNumeroCredito());
        Assertions.assertEquals(espero.getTipoCredito(),recibo.getTipoCredito());
    }

    @Test
    void findByIdCliente() {
    }

    @Test
    void findByIdClienteAndTipoCredito() {
    }

    @Test
    void update() {
        CreditoProductoDao expected = new CreditoProductoDao();
        expected.setId("2");
        expected.setNumeroCredito("0001461");
        expected.setTipoCredito("TAR");

        CreditoProductoDao cuenta = new CreditoProductoDao();
        cuenta.setId("2");
        cuenta.setNumeroCredito("0001461");
        cuenta.setTipoCredito("TAR");

        Mockito.when( creditoProductoRepository.save(Mockito.any(CreditoProductoDao.class)) )
                .thenReturn( Mono.just(cuenta) );
        log.info("step 1"+cuenta.toString());

        CreditoProductoDao actualiza=new CreditoProductoDao();
        expected.setId("2");
        expected.setNumeroCredito("0001461");
        expected.setTipoCredito("TAR");

        Mono<CreditoProductoDao> actual0 = creditoProductoRepository.save(actualiza);
        CreditoProductoDao actual=actual0.block();
        log.info("step 2"+actual.toString());

        Assertions.assertEquals(expected.getId(),actual.getId());
        Assertions.assertEquals(expected.getNumeroCredito(),actual.getNumeroCredito());
        Assertions.assertEquals(expected.getTipoCredito(),actual.getTipoCredito());
    }

    @Test
    void delete() {
    }
}
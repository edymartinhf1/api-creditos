package com.bootcamp.bank.creditos.service.impl;

import com.bootcamp.bank.creditos.model.dao.CreditoProductoDao;
import com.bootcamp.bank.creditos.model.dao.repository.CreditoProductoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@Log4j2
class CreditoServiceImplTest {


    @Mock
    private CreditoProductoRepository creditoProductoRepository;

    @InjectMocks
    private CreditoServiceImpl creditoService;

    @org.junit.jupiter.api.BeforeEach
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

        Mono<CreditoProductoDao> actual0 = creditoProductoRepository.save(actualiza);
        CreditoProductoDao actual=actual0.block();
        log.info("step 2"+actual.toString());

        Assertions.assertEquals(expected.getId(),actual.getId());
        Assertions.assertEquals(expected.getNumeroCredito(),actual.getNumeroCredito());
        Assertions.assertEquals(expected.getTipoCredito(),actual.getTipoCredito());
    }

    @Test
    void findAll() {
        CreditoProductoDao creditoProductoDao=new CreditoProductoDao();
        creditoProductoDao.setId("1");
        creditoProductoDao.setNumeroCredito("005-005-004");
        creditoProductoDao.setIdCliente("02");
        creditoProductoDao.setTipoCredito("PER");

        CreditoProductoDao creditoProductoDao1=new CreditoProductoDao();
        creditoProductoDao1.setId("1");
        creditoProductoDao1.setNumeroCredito("005-005-004");
        creditoProductoDao1.setIdCliente("02");
        creditoProductoDao1.setTipoCredito("PER");

        List<CreditoProductoDao> expected=new ArrayList<>();
        expected.add(creditoProductoDao);
        expected.add(creditoProductoDao1);
        log.info("test");
        Mockito.when( creditoProductoRepository.save(Mockito.any(CreditoProductoDao.class)) )
                .thenReturn( Mono.just(creditoProductoDao) );

        Mono<CreditoProductoDao> result1=creditoProductoRepository.save(creditoProductoDao);

        Mockito.when( creditoProductoRepository.save(Mockito.any(CreditoProductoDao.class)) )
                .thenReturn( Mono.just(creditoProductoDao1) );

        Mono<CreditoProductoDao> result2=creditoProductoRepository.save(creditoProductoDao1);
        result1.subscribe(operacionCtaDao -> log.info(operacionCtaDao.toString()));
        result2.subscribe(operacionCtaDao -> log.info(operacionCtaDao.toString()));

        Mockito.when( creditoProductoRepository.findAll())
                .thenReturn( Flux.fromIterable(expected));

        Flux<CreditoProductoDao> obtenidos = creditoProductoRepository.findAll();
        List<CreditoProductoDao> actual = obtenidos.map(operacionCtaDao -> operacionCtaDao).collectList().block();


        Assertions.assertEquals(expected.get(0).getId(), actual.get(0).getId());
        Assertions.assertEquals(expected.get(1).getId(), actual.get(1).getId());
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
package com.bootcamp.bank.creditos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "clientClientes")
    public WebClient webClientClientes() {
        return WebClient.create("http://localhost:8081");
    }

    @Bean(name = "clientConsumos")
    public WebClient webClientConsumos() {
        return WebClient.create("http://localhost:8085");
    }

    @Bean(name = "clientPagos")
    public WebClient webClientPagos() {
        return WebClient.create("http://localhost:8086");
    }

}
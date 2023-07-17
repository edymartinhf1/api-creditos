package com.bootcamp.bank;

import com.bootcamp.bank.creditos.service.CreditoServiceI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApiCreditosApplicationTests {
	@Autowired
	private CreditoServiceI creditoServiceI;

	@Test
	void contextLoads() {
		assertThat(creditoServiceI).isNotNull();

	}

}

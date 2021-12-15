package br.org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.blogpessoal.model.Tema;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemaControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	@Order(1)
	@DisplayName("Adicionar um Tema")
	public void deveAdicionarUmTema() {
		HttpEntity<Tema> requisicao = new HttpEntity<Tema> (new Tema(0L, "Vagas"));
		
		ResponseEntity<Tema> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/temas", HttpMethod.POST, requisicao, Tema.class);
		
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getDescricao(), resposta.getBody().getDescricao());
	}
	
}

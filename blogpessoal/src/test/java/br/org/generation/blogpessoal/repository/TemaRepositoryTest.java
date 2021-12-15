package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Tema;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemaRepositoryTest {
	
	@Autowired
	private TemaRepository temaRepository;
	
	@BeforeAll
	void start() {
		
		temaRepository.save(new Tema(0L, "BackEnd"));
		temaRepository.save(new Tema(0L, "FrontEnd"));
		temaRepository.save(new Tema(0L, "Dados"));
		temaRepository.save(new Tema(0L, "QA"));
	}
	
	@Test
	@DisplayName("Retorna 1 descrição")
	public void deveRetornarDescricaoTema() {
		Optional<Tema> tema = temaRepository.findByDescricao("BackEnd");
		assertTrue(tema.get().getDescricao().equals("BackEnd"));
	}
	
	@Test
	@DisplayName("Retorna 2 temas")
	public void deveRetornarDoisTemas() {
		
		List<Tema> listaDeTemas = temaRepository.findAllByDescricaoContainingIgnoreCase("End");
		assertEquals(2, listaDeTemas.size());
		assertTrue(listaDeTemas.get(0).getDescricao().equals("BackEnd"));
		assertTrue(listaDeTemas.get(1).getDescricao().equals("FrontEnd"));
	}
	
}

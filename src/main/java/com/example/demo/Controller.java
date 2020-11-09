package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
public class Controller implements WebMvcConfigurer{ //HABILITA O ROTEAMENTO DE ENDEREÇOS

	@Autowired
	private ManutencaoRepository repository;
	
	//PARA ACESSAR A PÁGINA PELO LOCALHOST:8080
	public void addViewControllers(ViewControllerRegistry index) {
		index.addViewController("/").setViewName("forward:/index.html");
	}
	
	
	//QUATRO MÉTODOS: GET, POST, PUT E DELETE
	//GET BY ID, GET BY NOME, GET ALL
	
	//MOSTRA TODOS OS DADOS DE TODAS AS TABELAS
	@GetMapping("/manutencoes")
	public List<ManutencaoTable> buscarTodos(){
		return repository.findAll();
	}
	/*
	//MOSTRA OS DADOS DA TABELA PELO ID
	@GetMapping("/manutencoes/id/{id}")
	public Optional<ManutencaoTable> buscarUm(@PathVariable Long id){ //PATH VARIABLE É UMA NOTAÇÃO UTILIZADA PARA PASSAR UM ID
		return repository.findById(id);
	}
	*/
	
	//MOSTRA OS DADOS DA TABELA PELO ID MOSTRANDO "NOT FOUND"
	@GetMapping("/manutencoes/id/{id}")
	public ResponseEntity<ManutencaoTable> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	//INSERE NA TABELA PELA MESMA URL DO GET ALL
	@PostMapping("/manutencoes")
	public ManutencaoTable criar(@RequestBody ManutencaoTable objetoManutencao) {
		repository.save(objetoManutencao);
		return objetoManutencao;
	}
	
	//ATUALIZA NA TABELA EM UM ID ESPECÍFICO
	@PutMapping("/manutencoes/{id}")
	public ManutencaoTable atualizar(@PathVariable Long id, @RequestBody ManutencaoTable objetinho) {
		objetinho.setId(id); //SERVE PARA FORÇAR A ALTERAÇÃO DO BODY COM BASE EM UM ID
		repository.save(objetinho); //SALVA O DADO EM UM ID ESPECÍFICO
		return objetinho;
	}
	
	@GetMapping ("/manutencoes/{nome}")
	public List<ManutencaoTable> buscarPorNome (@PathVariable String nome){
		return repository.findByNome(nome);
	}
	
	@GetMapping("/manutencoes/{nome}/{categoria}")
	public Optional<ManutencaoTable> findByNomeAndCategoria (@PathVariable String nome, @PathVariable String categoria){
		return repository.findByNomeAndCategoria(nome, categoria);
	}
	
	//MÉTODO DELETE
	@DeleteMapping("/manutencoes/{id}")
	public void deleteById(@PathVariable Long id){ //PATH VARIABLE É UMA NOTAÇÃO UTILIZADA PARA PASSAR UM ID
		repository.deleteById(id);
	}
	
	//VIEW = FRONT
	// CONTROLLER = REQUESTS E GETMAPPING
	
	// TODA VEZ QUE VOCÊ CLICAR NO BARRA, VOCÊ SERÁ DIRECIONADO PARA A INDEX
}

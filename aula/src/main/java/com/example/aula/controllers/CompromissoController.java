package com.example.aula.controllers;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula.entidades.Compromisso;
import com.example.aula.entidades.Contato;
import com.example.aula.repositories.CompromissosRepository;
import com.example.aula.services.CompromissoService;
import com.example.aula.services.ContatoService;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController {
	List<Compromisso> compromisso = new ArrayList<>();

	@Autowired
	CompromissosRepository repo;

	@Autowired
	CompromissoService service;
	
	@Autowired
	ContatoService serviceContato;
	
	@PostMapping
	public ResponseEntity<Compromisso> salvar(@RequestBody Compromisso compromisso) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(compromisso));
	}

	@PutMapping("/{idcompromisso}")
	public ResponseEntity<Object> alterar(@PathVariable("idcompromisso") Long idcompromisso,
			@RequestBody Compromisso compromisso) {
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idcompromisso, compromisso));

	}

	@DeleteMapping("/{idCompromisso}")
	public ResponseEntity<Object> excluir(@PathVariable("idCompromisso") Long idCompromisso) {
		service.excluir(idCompromisso);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@GetMapping
	public ResponseEntity<List<Compromisso>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	@GetMapping("/contato")
	public ResponseEntity<List<Compromisso>> getAllByContato(@RequestParam("idcontato") Long id) {
		Contato contato = serviceContato.consultar(id);
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByContato(contato));
	}
	@GetMapping("/data")
	public ResponseEntity<List<Compromisso>> getAllByData(@RequestParam("data") @DateTimeFormat(pattern = "yyyy-MM-dd")Date data) {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByData(data));
	}
	
	@GetMapping("/intervalo-data")
	public ResponseEntity<List<Compromisso>> getAllByIntervaloData(@RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findByEntreDatas(dataInicio, dataFim));
	}

	@GetMapping("/{idcompromisso}")
	public ResponseEntity<Object> consultar(@PathVariable("idcompromisso") Long idcompromisso) {

		Optional<Compromisso> opt = repo.findById(idcompromisso);
		try {
			Compromisso comp = opt.get();
			return ResponseEntity.status(HttpStatus.OK).body(comp);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado");
		}
	}
	
}
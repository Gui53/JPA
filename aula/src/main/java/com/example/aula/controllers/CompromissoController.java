package com.example.aula.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula.entidades.Compromisso;
import com.example.aula.repositories.CompromissosRepository;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {
	@Autowired
	CompromissosRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Compromisso>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	@PostMapping
	public ResponseEntity<Compromisso> save(@RequestBody Compromisso compromisso){
		Compromisso comp = repo.save(compromisso);
		return ResponseEntity.status(HttpStatus.CREATED).body(comp);
	}
}

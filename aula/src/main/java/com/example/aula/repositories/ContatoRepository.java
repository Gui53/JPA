package com.example.aula.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aula.entidades.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
	Contato findByEmail(String email);
	
	
}

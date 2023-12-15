package com.example.aula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aula.entidades.Compromisso;
import com.example.aula.entidades.Contato;
import com.example.aula.exceptions.RecursoNaoEncontrado;
import com.example.aula.exceptions.ValidaDadosException;
import com.example.aula.repositories.CompromissosRepository;

@Service
public class CompromissoService {
	@Autowired
	CompromissosRepository repo;
	
	public Compromisso consultar(Long idcompromissos) {
		Optional<Compromisso> opt = repo.findById(idcompromissos);
		Compromisso comp = opt.orElseThrow(() -> new RecursoNaoEncontrado("Compromisso não encontrado"));
		return comp;
	}
	public Compromisso consultarCt(Long contato_id) {
		Optional<Compromisso> opt = repo.findById(contato_id);
		Compromisso comp = opt.orElseThrow(() -> new RecursoNaoEncontrado("Compromisso não encontrado"));
		return comp;
	}
	public Compromisso salvar(Compromisso compromisso) {
		validaCampos(compromisso);
		return repo.save(compromisso);
	}
	public Compromisso alterar(Long idCompromisso, Compromisso compromisso) {
		Compromisso comp = consultar(idCompromisso);
		validaCampos(compromisso);
		comp = compromisso;
		return repo.save(comp);
	}
	public void excluir(Long id) {
		Compromisso comp = consultar(id);
		repo.delete(comp);
	}
	private void validaCampos(Compromisso compromisso) {
		if(compromisso.getDescricao().equals("")) {
			throw new ValidaDadosException("A descrição deve ser informado");
		}
		if(compromisso.getData().equals("")) {
			throw new ValidaDadosException("A data deve ser informado");
		}
		
		if(compromisso.getHora() == null ) {
			throw new ValidaDadosException("A Hora deve ser informado");
		}
		else {
			if(compromisso.getHora().equals("")) {
				throw new ValidaDadosException("A Hora deve ser informado");
			}
		}
		if(compromisso.getContato().equals("")) {
			throw new ValidaDadosException("O id contato deve ser informado");
		}
		if(compromisso.getLocal().equals("") ) {
			throw new ValidaDadosException("O id local deve ser informado");
		}
	}
}

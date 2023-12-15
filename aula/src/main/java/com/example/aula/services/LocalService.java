package com.example.aula.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aula.entidades.Contato;
import com.example.aula.entidades.Local;
import com.example.aula.exceptions.RecursoNaoEncontrado;
import com.example.aula.exceptions.ValidaDadosException;
import com.example.aula.repositories.LocalRepository;

@Service
public class LocalService {
	
	@Autowired
	LocalRepository repo;
	
	public Local consultar(Long idLocal) {
		Optional<Local> opt = repo.findById(idLocal);
		Local ct = opt.orElseThrow(() -> new RecursoNaoEncontrado("Local não encontrado."));
		return ct;	
	}
	public Local salvar(Local local) {
		validaCampos(local);
		return repo.save(local);
	}
	public Local alterar(Long idLocal, Local local) {
		Local lc = consultar(idLocal);
		validaCampos(local);
		lc = local;
		return repo.save(lc);
	}
	public void excluir(Long id) {
		Local lc = consultar(id);
		repo.delete(lc);
	}
	private void validaCampos(Local local) {
		if(local.getNome().equals("")) {
			throw new ValidaDadosException("O nome deve ser informado");
		}
		if(local.getRua().equals("")) {
			throw new ValidaDadosException("A rua deve ser informado");
		}
		if(local.getBairro().equals("")) {
			throw new ValidaDadosException("O bairro deve ser informado");
		}
		if(local.getCidade().equals("")) {
			throw new ValidaDadosException("A idade deve ser informado");
		}
		if(local.getUf().equals("")) {
			throw new ValidaDadosException("O UF deve ser informado");
		}
		if(local.getCep().equals("")) {
			throw new ValidaDadosException("O Cep deve ser informado");
		}
	}
}

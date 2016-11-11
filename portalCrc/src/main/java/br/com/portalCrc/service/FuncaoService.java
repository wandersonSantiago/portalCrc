package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcao;
import br.com.portalCrc.repository.FuncaoRepository;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class FuncaoService {
	
	@Autowired 
	private FuncaoRepository funcaoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Funcao funcao){
		funcaoRepository.save(funcao);
	}
	
	public Collection<Funcao> lista(){
		return funcaoRepository.findAll();
	}
	
	public Funcao buscaPorId(Long id){
		return funcaoRepository.findOne(id);
	}

}

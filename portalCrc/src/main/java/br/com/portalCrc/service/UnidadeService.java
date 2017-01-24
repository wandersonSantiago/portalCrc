package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.repository.UnidadeRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UnidadeService {

	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Unidade unidade){
		unidadeRepository.save(unidade);
	}
	
	public Collection<Unidade> lista(){
		return unidadeRepository.findAll();
	}
	
	public Unidade buscaPorId(Long id){
		return unidadeRepository.findOne(id);
	}

	public Iterable<Unidade> buscaUnidadePorId(Long id) {
		
		return unidadeRepository.buscaUnidadePorId(id);
	}
}

package br.com.portalCrc.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.repository.SetorRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Setor setor){
		setorRepository.save(setor);
	}
	
	public Collection<Setor> lista(){
		return setorRepository.findAll();
	}
	
	public Setor buscaPorId(Long id){
		return setorRepository.findOne(id);
	}
}

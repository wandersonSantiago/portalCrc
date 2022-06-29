package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Pais;
import br.com.portalCrc.repository.PaisRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PaisService {
	
	@Autowired
	private PaisRepository cargoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Pais cargo){
		cargoRepository.save(cargo);
	}
	
	public Collection<Pais> lista(){
		return cargoRepository.findAll();
	}
	
	public Pais buscaPorId(Long id){
		return cargoRepository.findOne(id);
	}

}

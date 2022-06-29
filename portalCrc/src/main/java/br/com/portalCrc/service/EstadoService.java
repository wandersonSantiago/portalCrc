package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Estado;
import br.com.portalCrc.repository.EstadoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EstadoService {

	@Autowired
	private EstadoRepository cargoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Estado cargo){
		
		cargoRepository.save(cargo);
	}
	
	public Collection<Estado> lista(Integer idPais){
		return cargoRepository.findByPais_id(idPais);
	}
	
	public Estado buscaPorId(Long id){
		return cargoRepository.findOne(id);
	}
}

package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Cargo;
import br.com.portalCrc.repository.CargoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Cargo cargo){
		cargoRepository.save(cargo);
	}
	
	public Collection<Cargo> lista(){
		return cargoRepository.findAll();
	}
	
	public Cargo buscaPorId(Long id){
		return cargoRepository.findOne(id);
	}

}

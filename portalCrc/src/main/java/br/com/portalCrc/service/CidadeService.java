package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Cidade;
import br.com.portalCrc.repository.CidadeRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CidadeService {

	@Autowired
	private CidadeRepository cargoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Cidade cargo){
		
		cargoRepository.save(cargo);
	}
	
	public Collection<Cidade> lista(Integer idEstado){
		return cargoRepository.findByEstado_idOrderByNome(idEstado);
	}
	
	public Cidade buscaPorId(Long id){
		return cargoRepository.findOne(id);
	}
}

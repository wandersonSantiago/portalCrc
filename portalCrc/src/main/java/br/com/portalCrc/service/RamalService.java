package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Ramal;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.RamalRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RamalService {
	
	@Autowired 
	private RamalRepository ramalRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Ramal ramal){
		ramal.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		ramalRepository.save(ramal);
	}
	
	public Collection<Ramal> lista(){
		return ramalRepository.findAll();
	}
	
	public Ramal buscaPorId(Long id){
		return ramalRepository.findOne(id);
	}

	public Iterable<Ramal> buscarRamalPorUnidade(Long id) {
		
		return ramalRepository.buscarRamalPorUnidade(id);
	}

}

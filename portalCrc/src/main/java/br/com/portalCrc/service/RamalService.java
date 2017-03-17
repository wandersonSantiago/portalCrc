package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

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
		ramal.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		ramal.setDataCadastro(new Date());
		ramalRepository.save(ramal);
	}
	
	public Collection<Ramal> lista(){
		return ramalRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getUnidade().getId());
	}
	
	public Ramal buscaPorId(Long id){
		return ramalRepository.findOne(id);
	}

	public Iterable<Ramal> buscarRamalPorUnidade(Long id) {		
		return ramalRepository.buscarRamalPorUnidade(id);
	}

}

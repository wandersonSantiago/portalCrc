package br.com.portalCrc.service.chamado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.chamado.ModuloSistema;
import br.com.portalCrc.repository.chamado.ModuloSistemaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ModuloSistemaService {
	
	
	@Autowired
	private ModuloSistemaRepository moduloSistemaRepository;
	

	@Transactional(readOnly = false)
	public void salvarOuEditar(ModuloSistema moduloSistema){
		moduloSistemaRepository.save( moduloSistema);
	}
	
	public List<ModuloSistema> findAll(){
		return moduloSistemaRepository.findAll();
	}

	public ModuloSistema buscaPorId(Long id) {
		return moduloSistemaRepository.findOne(id);
	}

	public Iterable<ModuloSistema> findBySistema_id(Long idSistema) {
		return moduloSistemaRepository.findBySistema_id(idSistema);
	}

}

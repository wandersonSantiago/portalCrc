package br.com.portalCrc.service.chamado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.chamado.ModuloSistema;
import br.com.portalCrc.pojo.SessionUsuario;
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
		return moduloSistemaRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

	public ModuloSistema buscaPorId(Long id) {
		return moduloSistemaRepository.findOne(id);
	}

}

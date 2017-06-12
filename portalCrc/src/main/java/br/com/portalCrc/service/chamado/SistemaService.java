package br.com.portalCrc.service.chamado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.chamado.Sistema;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.SistemaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SistemaService {
	
	
	@Autowired
	private SistemaRepository sistemaRepository;
	

	@Transactional(readOnly = false)
	public void salvarOuEditar(Sistema sistema){
		sistema.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		sistemaRepository.save( sistema);
	}
	
	public List<Sistema> findAll(){
		return sistemaRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

	public Sistema buscaPorId(Long id) {
		return sistemaRepository.findOne(id);
	}


}

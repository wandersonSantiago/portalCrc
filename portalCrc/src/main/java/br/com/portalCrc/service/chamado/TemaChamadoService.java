package br.com.portalCrc.service.chamado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.TemaChamadoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TemaChamadoService {

	
	@Autowired
	private TemaChamadoRepository temaChamadoRepository;
	

	@Transactional(readOnly = false)
	public void salvarOuEditar(TemaChamado temaChamado){
		temaChamadoRepository.save( temaChamado);
	}
	
	public List<TemaChamado> findAll(){
		return temaChamadoRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

}

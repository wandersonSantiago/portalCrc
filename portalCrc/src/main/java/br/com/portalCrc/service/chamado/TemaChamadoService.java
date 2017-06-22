package br.com.portalCrc.service.chamado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.chamado.ModuloSistema;
import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.enums.chamado.TipoEquipamentoChamado;
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
		return temaChamadoRepository.findAll();
	}

	public TemaChamado buscaPorId(Long id) {
		return temaChamadoRepository.findOne(id);
	}

	public Iterable<TemaChamado> findByTipoEquipamento(TipoEquipamentoChamado tema) {
		return temaChamadoRepository.findByTipoEquipamento(tema);
	}

	public Iterable<TemaChamado> findByModulo_id(Long idModulo) {
		return temaChamadoRepository.findByModulo_id(idModulo);
	}

}

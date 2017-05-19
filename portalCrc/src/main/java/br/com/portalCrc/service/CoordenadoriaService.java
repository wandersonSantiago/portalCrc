package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Coordenadoria;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.CoordenadoriaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CoordenadoriaService {

	@Autowired
	private CoordenadoriaRepository coordenadoriaRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Coordenadoria coordenadoria){
		coordenadoria.setSecretaria(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getSecretaria());
		coordenadoria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		coordenadoria.setDataCadastro(new Date());
		coordenadoriaRepository.save(coordenadoria);
	}
	
	public Collection<Coordenadoria> lista(){
		return coordenadoriaRepository.findBySecretaria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getSecretaria().getId());
	}
	
	public Coordenadoria buscaPorId(Long id){
		return coordenadoriaRepository.findOne(id);
	}
}

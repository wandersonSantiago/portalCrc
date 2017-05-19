package br.com.portalCrc.service;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.SetorRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Setor setor){
		setor.setDataCadastro(new Date());
		setor.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		setorRepository.save(setor);
	}
	
	public Collection<Setor> lista(){
		return setorRepository.findAll();
	}
	public Collection<Setor> listaPorTipoUnidade(){
		return setorRepository.findByTipoUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getTipoUnidade().getId());
	}
	
	public Setor buscaPorId(Long id){
		return setorRepository.findOne(id);
	}
}

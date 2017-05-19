package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcao;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.FuncaoRepository;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class FuncaoService {
	
	@Autowired 
	private FuncaoRepository funcaoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Funcao funcao){
		funcao.setSecretaria(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getSecretaria());
		funcao.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		funcao.setDataCadastro(new Date());
		funcaoRepository.save(funcao);
	}
	
	public Collection<Funcao> lista(){
		return funcaoRepository.findBySecretaria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getSecretaria().getId());
	}
	
	public Funcao buscaPorId(Long id){
		return funcaoRepository.findOne(id);
	}

}

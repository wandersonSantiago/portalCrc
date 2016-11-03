package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.repository.FuncionarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioService {

	@Autowired 
	private FuncionarioRepository funcionarioRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Funcionario funcionario){
		funcionarioRepository.save(funcionario);
	}
	
	public Collection<Funcionario> lista(){
		return funcionarioRepository.findAll();
	}
	
	public Funcionario buscaPorId(Long id){
		return funcionarioRepository.findOne(id);
	}
}

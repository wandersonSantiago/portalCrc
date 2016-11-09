package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.FuncionarioUnidade;
import br.com.portalCrc.repository.FuncionarioUnidadeRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioUnidadeService {

	
	@Autowired 
	private FuncionarioUnidadeRepository funcionarioUnidadeRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(FuncionarioUnidade funcionarioUnidade){
		funcionarioUnidadeRepository.save(funcionarioUnidade);
	}
	
	public Collection<FuncionarioUnidade> lista(){
		return funcionarioUnidadeRepository.findAll();
	}
	
	public FuncionarioUnidade buscaPorId(Long id){
		return funcionarioUnidadeRepository.findOne(id);
	}
}

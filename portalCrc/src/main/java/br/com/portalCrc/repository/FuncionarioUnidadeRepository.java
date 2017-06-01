package br.com.portalCrc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.FuncionarioUnidade;

public interface FuncionarioUnidadeRepository extends JpaRepository<FuncionarioUnidade, Long>{

	FuncionarioUnidade findTop1ByFuncionario_idOrderByIdDesc(Long id);	

	
	List<FuncionarioUnidade> findDistinctFuncionario_cpfByUnidade_idAndFuncionarioPessoaNomeCompletoIgnoreCaseContaining(
			Long unidade, String texto);

	List<FuncionarioUnidade> findDistinctFuncionario_cpfByUnidade_idAndFuncionarioPessoaCpf(Long unidade,
			String string);

}

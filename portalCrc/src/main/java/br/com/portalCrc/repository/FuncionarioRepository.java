package br.com.portalCrc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	
	Iterable<Funcionario> findByUnidadeAtual_id(Long id);
	


	List<Funcionario> findByUnidadeAtual_idAndPessoaNomeCompletoIgnoreCaseContaining(Long unidade, String texto);


	Funcionario findByPessoa_cpfAndUnidadeAtual_id(String cpf, Long idUnidade);


	List<Funcionario> findByUnidadeAtual_idAndPessoa_cpf(Long unidade, String string);

	
}

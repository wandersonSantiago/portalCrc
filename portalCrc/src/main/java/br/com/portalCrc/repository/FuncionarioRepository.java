package br.com.portalCrc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	
	Iterable<Funcionario> findByUnidadeAtual_id(Long id);

	Funcionario findByPessoa_cpfAndUnidadeAtual_id(String cpf, Long idUnidade);

	Page<Funcionario> findByUnidadeAtual_idAndPessoaNomeCompletoIgnoreCaseContaining(Long unidade, String texto,
			Pageable page);

	Page<Funcionario> findByUnidadeAtual_idAndPessoa_cpf(Long unidade, String string, Pageable page);

	
}

package br.com.portalCrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	
	Iterable<Funcionario> findByUnidadeAtual_id(Long id);

	
}

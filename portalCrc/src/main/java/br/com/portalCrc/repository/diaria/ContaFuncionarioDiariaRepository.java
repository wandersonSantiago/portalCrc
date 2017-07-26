package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.diaria.ContaFuncionarioDiaria;

public interface ContaFuncionarioDiariaRepository  extends JpaRepository<ContaFuncionarioDiaria, Long>{

	
	
	
	Iterable<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtualCoordenadoriaSecretaria_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtualCoordenadoria_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtual_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioPessoaCpfAndStatus(String string, boolean status);

	List<ContaFuncionarioDiaria> findByFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndStatus(String texto, boolean status);

	ContaFuncionarioDiaria findByFuncionario_idAndStatus(Long id, boolean status);

	
}

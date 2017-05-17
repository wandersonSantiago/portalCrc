package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.diaria.ContaFuncionarioDiaria;

public interface ContaFuncionarioDiariaRepository  extends JpaRepository<ContaFuncionarioDiaria, Long>{

	
	
	
	Iterable<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtualCoordenadoriaSecretaria_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtualCoordenadoria_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtual_id(Long id);

	ContaFuncionarioDiaria findByFuncionario_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioPessoaCpf(String string);

	List<ContaFuncionarioDiaria> findByFuncionarioPessoaNomeCompletoIgnoreCaseContaining(String texto);

}

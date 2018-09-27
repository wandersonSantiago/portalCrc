package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.diaria.ContaFuncionarioDiaria;

public interface ContaFuncionarioDiariaRepository  extends JpaRepository<ContaFuncionarioDiaria, Long>{

	
	
	
	Iterable<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtualCoordenadoriaSecretaria_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtualCoordenadoria_id(Long id);

	List<ContaFuncionarioDiaria> findByFuncionarioUnidadeAtual_id(Long id);

	Page<ContaFuncionarioDiaria> findByFuncionarioPessoaCpfIgnoreCaseContainingAndStatus(String string, boolean status, Pageable page);

	Page<ContaFuncionarioDiaria> findByFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndStatus(String texto, boolean status,  Pageable page);

	ContaFuncionarioDiaria findByFuncionario_idAndStatus(Long id, boolean status);

	List<ContaFuncionarioDiaria> findByFuncionario_id(Long id);

	
}

package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;

public interface FuncionarioDiariaRepository extends JpaRepository<FuncionarioDiaria, Long>{

	List<FuncionarioDiaria> findByUnidade_id(Long id, Long id2);

	Iterable<FuncionarioDiaria> findByUnidadeCoordenadoriaSecretaria_id(Long id);

	@Query("From FuncionarioDiaria diaria WHERE diaria.unidade.id = ?1 AND diaria.diaria.id = ?2 AND diaria.totalValorDiaria != '0'")
	List<FuncionarioDiaria> findByUnidade_idAndDiaria_id(Long id, Long id2);

	FuncionarioDiaria findByUnidade_idAndContaFuncionario_idAndDiaria_id(Long id, Long idFuncionario, Long idDiaria);

	FuncionarioDiaria findById(Long id);

	@Query("From FuncionarioDiaria diaria WHERE diaria.diaria.id = ?1 AND diaria.totalValorDiaria != '0'")
	List<FuncionarioDiaria> findByDiaria_id(Long id);

	@Query("From FuncionarioDiaria diaria WHERE diaria.unidade.coordenadoria.id = ?1 AND diaria.diaria.id = ?2 AND diaria.totalValorDiaria != '0'")
	List<FuncionarioDiaria> findByUnidade_coordenadoria_idAndDiaria_id(Long id, Long id2);

	Page<FuncionarioDiaria> findByUnidade_idAndContaFuncionario_funcionario_pessoa_nomeCompletoIgnoreCaseContainingOrContaFuncionario_funcionario_pessoa_cpfContaining(Long idUnidade, String string, Pageable page);

	FuncionarioDiaria findByUnidade_idAndContaFuncionario_funcionario_idAndDiaria_id(Long id, Long idFuncionario,
			Long idDiaria);


	int countByDiaria_idAndContaFuncionario_id(Long id, Long id2);

	Page<FuncionarioDiaria> findAllByDiaria_id(Long idDiaria, Pageable page);

	Page<FuncionarioDiaria> findByContaFuncionarioFuncionarioPessoaCpfAndDiaria_id(String string, Long idDiaria,
			Pageable page);

	Page<FuncionarioDiaria> findByContaFuncionarioFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndDiaria_id(
			String texto, Long idDiaria, Pageable page);

	List<FuncionarioDiaria> findByUnidade_id(Long id);

	
	
}

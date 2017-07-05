package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ItemDiaria;

public interface FuncionarioDiariaRepository extends JpaRepository<FuncionarioDiaria, Long>{

	//List<FuncionarioDiaria> findByUnidadeCoordenadoria_idAndDiaria_id(Long id, Long id2);

	List<FuncionarioDiaria> findByUnidade_id(Long id, Long id2);

	Iterable<FuncionarioDiaria> findByUnidadeCoordenadoriaSecretaria_id(Long id);

	List<FuncionarioDiaria> findByUnidade_idAndDiaria_id(Long id, Long id2);

	FuncionarioDiaria findByUnidade_idAndContaFuncionario_idAndDiaria_id(Long id, Long idFuncionario, Long idDiaria);

	FuncionarioDiaria findById(Long id);

	Iterable<FuncionarioDiaria> findByDiaria_id(Long id);

	List<FuncionarioDiaria> findByUnidade_coordenadoria_idAndDiaria_id(Long id, Long id2);

	Page<FuncionarioDiaria> findByUnidade_idAndContaFuncionario_funcionario_pessoa_nomeCompletoIgnoreCaseContainingOrContaFuncionario_funcionario_pessoa_cpfContaining(Long idUnidade, String string, Pageable page);

	

}

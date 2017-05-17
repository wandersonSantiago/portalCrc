package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;

public interface FuncionarioDiariaRepository extends JpaRepository<FuncionarioDiaria, Long>{

	List<FuncionarioDiaria> findByUnidadeCoordenadoria_idAndDiaria_id(Long id, Long id2);

	List<FuncionarioDiaria> findByUnidade_id(Long id, Long id2);

	Iterable<FuncionarioDiaria> findByUnidadeCoordenadoriaSecretaria_id(Long id);

	Iterable<FuncionarioDiaria> findByUnidade_idAndDiaria_id(Long id, Long id2);

}

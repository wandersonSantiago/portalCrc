package br.com.portalCrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long>{

	@Query("From Unidade u where u.coordenadoria.id = ?1")
	Iterable<Unidade> buscaUnidadePorId(Long id);

}

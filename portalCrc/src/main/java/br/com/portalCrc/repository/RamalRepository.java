package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.Ramal;

public interface RamalRepository extends JpaRepository<Ramal, Long> {

	@Query("From Ramal ramal where ramal.unidade.id = ?1")
	Iterable<Ramal> buscarRamalPorUnidade(Long id);

	Collection<Ramal> findByUnidade_id(Long id);

}

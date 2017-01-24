package br.com.portalCrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.ListaRamal;

public interface ListaRamalRepository extends JpaRepository<ListaRamal, Long> {

	@Query("From ListaRamal ramal where ramal.unidade.id = ?1")
	Iterable<ListaRamal> buscarRamalPorUnidade(Long id);

}

package br.com.portalCrc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.ListaTelefone;

public interface ListaTelefoneRepository extends JpaRepository<ListaTelefone, Long> {

	@Query("From ListaTelefone lista where lista.unidade.id = ?1")
	List<ListaTelefone> listaTelefoneRepository(Long id);

}

package br.com.portalCrc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

	@Query("From Telefone fone where fone.unidade.id = ?1")
	List<Telefone> listaTelefoneRepository(Long id);

}

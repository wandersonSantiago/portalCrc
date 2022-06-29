package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Coordenadoria;

public interface CoordenadoriaRepository extends JpaRepository<Coordenadoria, Long> {

	Collection<Coordenadoria> findBySecretaria_id(Long id);

}

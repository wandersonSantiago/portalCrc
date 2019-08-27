package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

	Collection<Cargo> findBySecretaria_idOrderByDescricao(Long id);

}

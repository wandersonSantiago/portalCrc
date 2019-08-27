package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long>{

	Collection<Setor> findByTipoUnidade_id(Long id);

}

package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.TipoUnidade;

public interface TipoUnidadeRepository extends JpaRepository<TipoUnidade, Long>{

	Collection<TipoUnidade> findBySecretaria_id(Long id);

}

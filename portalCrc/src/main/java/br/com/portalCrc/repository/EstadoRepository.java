package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

	Collection<Estado> findByPais_id(Long idPais);

}

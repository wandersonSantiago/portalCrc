package br.com.portalCrc.repository.infra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.infra.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{

	List<Modelo> findByMarca_id(Long idMarca);

}

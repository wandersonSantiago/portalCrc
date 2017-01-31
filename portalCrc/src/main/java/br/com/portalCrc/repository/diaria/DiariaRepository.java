package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.diaria.Diaria;

public interface DiariaRepository extends JpaRepository<Diaria, Long>{

	
	@Query("From Diaria d where d.unidade.id = ?1")
	List<Diaria> listaUnidade(Long id);
	
	@Query("From Diaria d where d.unidade.coordenadoria.id = ?1")
	List<Diaria> listaCoordenadoria(Long id);
	
}

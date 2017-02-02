package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.diaria.ItemDiaria;

public interface ItemDiariaRepository extends JpaRepository<ItemDiaria, Long>{

	@Query("From ItemDiaria d where d.unidade.id = ?1 AND d.diaria.id = ?2")
	List<ItemDiaria> listaUnidade(Long id, Long idDiaria);
	
	@Query("From ItemDiaria d where d.unidade.coordenadoria.id = ?1 AND d.diaria.id = ?2")
	List<ItemDiaria> findByUnidadeCoordenadoria_idAndDiaria_id(Long id, Long idDiaria);
	
}

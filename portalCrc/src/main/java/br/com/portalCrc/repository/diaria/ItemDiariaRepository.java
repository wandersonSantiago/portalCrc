package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;

public interface ItemDiariaRepository extends JpaRepository<ItemDiaria, Long>{

	@Query("From ItemDiaria d where d.funcionarioDiaria.unidade.id = ?1 AND d.funcionarioDiaria.diaria.id = ?2")
	List<ItemDiaria> listaUnidade(Long id, Long idDiaria);
	
	@Query("From ItemDiaria d where d.funcionarioDiaria.unidade.coordenadoria.id = ?1 AND d.funcionarioDiaria.diaria.id = ?2")
	List<ItemDiaria> findByUnidadeCoordenadoria_idAndDiaria_id(Long id, Long idDiaria);

	@Query("From ItemDiaria d where  d.funcionarioDiaria.diaria.id = ?1")
	List<ItemDiaria> listaSecretaria(Long id);

	Iterable<ItemDiaria> findByFuncionarioDiaria_id(Long id);

	Iterable<ItemDiaria> findByFuncionarioDiaria_diaria_idAndFuncionarioDiaria_contaFuncionario_funcionario_id(
			Long idDiaria, Long idFuncionario);

	List<ItemDiaria> findByFuncionarioDiaria_idAndTipo(Long idFuncionario, TipoDiariaEnum tipo);
	
}

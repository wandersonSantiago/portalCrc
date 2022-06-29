package br.com.portalCrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long>{

	@Query("From Unidade u where u.coordenadoria.id = ?1 AND u.tipoUnidade.mnemonico != 'COORDENADORIA'")
	Iterable<Unidade> buscaUnidadePorCoordenadoria(Long id);

	Iterable<Unidade> findByCoordenadoria_id(Long id);

	Iterable<Unidade> findByTipoUnidadeMnemonico(String coordenadoria);

	@Query("From Unidade u where u.coordenadoria.id = ?1 AND u.tipoUnidade.mnemonico = ?2 ")
	Iterable<Unidade> findByCoordenadoria_IdByTipoUnidadeMnemonico(Long id, String tipo);

	Iterable<Unidade> findByCoordenadoria_idAndTipoUnidade_id(Long idCoordenadoria, Long idTipo);

}

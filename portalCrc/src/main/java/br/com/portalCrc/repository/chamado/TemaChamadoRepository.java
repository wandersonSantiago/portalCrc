package br.com.portalCrc.repository.chamado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.enums.chamado.TipoTema;

public interface TemaChamadoRepository extends JpaRepository<TemaChamado, Long>{

	List<TemaChamado> findByUnidade_id(Long id);

	Iterable<TemaChamado> findByTipoEquipamento(TipoTema tema);

	Iterable<TemaChamado> findByModulo_id(Long idModulo);

}

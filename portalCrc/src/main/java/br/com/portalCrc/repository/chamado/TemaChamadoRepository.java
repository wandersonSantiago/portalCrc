package br.com.portalCrc.repository.chamado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.chamado.ModuloSistema;
import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.enums.chamado.TipoEquipamentoChamado;

public interface TemaChamadoRepository extends JpaRepository<TemaChamado, Long>{

	List<TemaChamado> findByUnidade_id(Long id);

	Iterable<TemaChamado> findByTipoEquipamento(TipoEquipamentoChamado tema);

	Iterable<TemaChamado> findByModulo_id(Long idModulo);

}

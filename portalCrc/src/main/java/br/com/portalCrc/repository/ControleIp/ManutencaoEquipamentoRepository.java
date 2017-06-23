package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.ManutencaoEquipamento;

public interface ManutencaoEquipamentoRepository extends JpaRepository<ManutencaoEquipamento, Long> {

	Collection<ManutencaoEquipamento> findByEquipamento_unidade_id(Long id);

	ManutencaoEquipamento findTop1ByEquipamento_idOrderByIdDesc(Long id);

	Iterable<ManutencaoEquipamento> findByDataPreventivaLessThanEqualAndStatusAndEquipamento_unidade_idAndTecnicoIsNull(
			Date dataFinal, Boolean status, Long idUnidade);

	Iterable<ManutencaoEquipamento> findByStatusAndEquipamento_unidade_idAndTecnicoIsNull(Boolean status,
			Long idUnidade);

	Iterable<ManutencaoEquipamento> findByStatusAndEquipamento_unidade_idAndTecnicoIsNotNull(Boolean status,
			Long idUnidade);

}

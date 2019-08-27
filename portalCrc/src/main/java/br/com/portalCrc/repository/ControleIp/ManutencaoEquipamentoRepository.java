package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.ManutencaoEquipamento;

public interface ManutencaoEquipamentoRepository extends JpaRepository<ManutencaoEquipamento, Long> {

	Collection<ManutencaoEquipamento> findByEquipamento_unidade(Long id);

	ManutencaoEquipamento findTop1ByEquipamento_idOrderByIdDesc(Long id);

	Iterable<ManutencaoEquipamento> findByDataPreventivaLessThanEqualAndStatusAndEquipamento_unidadeAndTecnicoIsNull(
			Date dataFinal, Boolean status, Long idUnidade);

	Iterable<ManutencaoEquipamento> findByStatusAndEquipamento_unidadeAndTecnicoIsNull(Boolean status,
			Long idUnidade);

	Iterable<ManutencaoEquipamento> findByStatusAndEquipamento_unidadeAndTecnicoIsNotNull(Boolean status,
			Long idUnidade);

}

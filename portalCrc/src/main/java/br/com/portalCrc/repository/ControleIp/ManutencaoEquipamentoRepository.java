package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.portalCrc.entity.controleIp.ManutencaoEquipamento;

public interface ManutencaoEquipamentoRepository extends JpaRepository<ManutencaoEquipamento, Long> {

	Collection<ManutencaoEquipamento> findByEquipamento_unidade_id(Long id);

	ManutencaoEquipamento findTop1ByEquipamento_idOrderByIdDesc(Long id);

	Iterable<ManutencaoEquipamento> findByStatusAndEquipamento_unidade_id(Boolean status, Long idUnidade);

	@Query("FROM ManutencaoEquipamento manutencao WHERE manutencao.dataPreventiva <= :dataFinal "
			+ "AND manutencao.status = :status AND manutencao.equipamento.unidade.id = :id")
	Collection<ManutencaoEquipamento> findByStatusAndEquipamento_unidade_idAndDataPreventivaBETWEENDataPreventiva(
			@Param(value = "dataFinal") Date dataFinal, @Param(value = "status") Boolean status,
			@Param(value = "id") Long id);

}

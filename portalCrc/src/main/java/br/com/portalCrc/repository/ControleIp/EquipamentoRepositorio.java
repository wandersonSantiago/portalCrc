package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.enums.controleIp.StatusEquipamento;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;

public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Long> {

	Collection<Equipamento> findByUnidade(Long id);

	Iterable<Equipamento> findByStatusAndUnidade(StatusEquipamento status, Long id);

	Equipamento findByPatrimonioIgnoreCase(String patrimonio);

	
	//repositorio para abrir transação por tipo
	List<Equipamento> findByTipoEquipamento(TipoEquipamentoEnum tipo);

}

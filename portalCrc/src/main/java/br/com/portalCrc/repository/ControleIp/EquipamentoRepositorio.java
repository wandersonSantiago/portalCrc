package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.enums.controleIp.StatusEquipamento;

public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Long> {

	Collection<Equipamento> findByUnidade(Long id);

	Iterable<Equipamento> findByStatusAndUnidade(StatusEquipamento status, Long id);

	Equipamento findByPatrimonioIgnoreCase(String patrimonio);

}

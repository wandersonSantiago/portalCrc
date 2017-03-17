package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Equipamento;

public interface EquipamentoRepositorio extends JpaRepository<Equipamento, Long> {

	Collection<Equipamento> findByUnidade_id(Long id);
	
	
     

}

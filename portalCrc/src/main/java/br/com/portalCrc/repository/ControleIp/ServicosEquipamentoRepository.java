package br.com.portalCrc.repository.ControleIp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.ServicosEquipamento;

public interface ServicosEquipamentoRepository extends JpaRepository<ServicosEquipamento, Long> {

	List<ServicosEquipamento> findByEquipamento_id(Long idEquipamento);

}

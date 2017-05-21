package br.com.portalCrc.repository.ControleIp;




import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Switch;
import br.com.portalCrc.enums.controleIp.StatusSwitch;

public interface SwitchRepositorio extends JpaRepository<Switch , Long > {

	Collection<Switch> findByUnidade_id(Long id);

	Iterable<Switch> findByStatusAndUnidade_id(StatusSwitch status, Long id);

	

}

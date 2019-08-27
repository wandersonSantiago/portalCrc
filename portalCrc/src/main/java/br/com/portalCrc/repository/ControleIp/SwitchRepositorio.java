package br.com.portalCrc.repository.ControleIp;




import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Switch;
import br.com.portalCrc.enums.controleIp.StatusSwitch;

public interface SwitchRepositorio extends JpaRepository<Switch , Long > {

	Collection<Switch> findByUnidade(Long id);

	Iterable<Switch> findByStatusAndUnidade(StatusSwitch status, Long id);

	

}

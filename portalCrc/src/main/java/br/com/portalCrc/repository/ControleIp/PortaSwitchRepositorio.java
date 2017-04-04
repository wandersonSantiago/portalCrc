package br.com.portalCrc.repository.ControleIp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.controleIp.PortaSwitch;

public interface PortaSwitchRepositorio extends JpaRepository<PortaSwitch, Long> {



	@Query("From PortaSwitch p where p.switchs.id = ?1 AND p.emUso = 'false' AND p.status = 'ATIVO' ORDER BY p.id ASC"  )
	Iterable<PortaSwitch> listaPortaLivre(Long id);

}

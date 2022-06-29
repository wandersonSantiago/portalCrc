package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.enums.controleIp.StatusIp;

public interface IpRepositorio extends JpaRepository<Ip, Long>{

	Collection<Ip> findByUnidade(Long id);

	

	Iterable<Ip> findByStatusAndUnidadeOrderByIdAsc(StatusIp status, Long id);

}

package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Ip;

public interface IpRepositorio extends JpaRepository<Ip, Long>{

	Collection<Ip> findByUnidade_id(Long id);

	

	Iterable<Ip> findByEmUsoAndUnidade_id(boolean b, Long id);

}

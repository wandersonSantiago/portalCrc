package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.TipoIp;

public interface TipoIpRepositorio extends JpaRepository<TipoIp , Long> {

	Collection<TipoIp> findByUnidade_id(Long id);


}

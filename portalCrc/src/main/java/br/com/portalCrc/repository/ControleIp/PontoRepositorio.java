package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Ponto;

public interface PontoRepositorio extends JpaRepository<Ponto , Long> {

	Collection<Ponto> findByUnidade_id(Long id);

}

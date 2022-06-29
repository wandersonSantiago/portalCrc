package br.com.portalCrc.repository.ControleIp;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Ponto;
import br.com.portalCrc.enums.controleIp.StatusPonto;

public interface PontoRepositorio extends JpaRepository<Ponto , Long> {

	Collection<Ponto> findByUnidade(Long id);


	Iterable<Ponto> findByStatusAndUnidade(StatusPonto status, Long id);

}

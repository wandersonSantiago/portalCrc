package br.com.portalCrc.repository.ControleIp;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.controleIp.Ponto;

public interface PontoRepositorio extends JpaRepository<Ponto , Long> {

}

package br.com.portalCrc.repository.chamado;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.chamado.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}

package br.com.portalCrc.repository.chamado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.portalCrc.entity.chamado.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> , JpaSpecificationExecutor<Mensagem>, QueryDslPredicateExecutor<Mensagem>{


}

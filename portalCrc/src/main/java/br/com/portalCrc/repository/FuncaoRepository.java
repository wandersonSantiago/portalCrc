package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Funcao;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

	Collection<Funcao> findBySecretaria_idOrderByDescricao(Long long1);

}

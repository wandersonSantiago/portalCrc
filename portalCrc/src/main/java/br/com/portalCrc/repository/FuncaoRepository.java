package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Funcao;
import br.com.portalCrc.entity.Secretaria;

public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

	Collection<Funcao> findBySecretaria_id(Secretaria secretaria);

}

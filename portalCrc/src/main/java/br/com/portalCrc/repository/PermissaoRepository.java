package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	Collection<Permissao> findById(Long id);

}

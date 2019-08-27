package br.com.portalCrc.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	Collection<Cidade> findByEstado_idOrderByNome(Integer idEstado);

	

}

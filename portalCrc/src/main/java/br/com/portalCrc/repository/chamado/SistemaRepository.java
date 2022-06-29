package br.com.portalCrc.repository.chamado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.chamado.Sistema;

public interface SistemaRepository extends JpaRepository<Sistema, Long>{

	List<Sistema> findByUnidade_id(Long id);

}

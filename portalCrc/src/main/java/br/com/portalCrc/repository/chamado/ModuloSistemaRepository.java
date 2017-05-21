package br.com.portalCrc.repository.chamado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.chamado.ModuloSistema;

public interface ModuloSistemaRepository extends JpaRepository<ModuloSistema, Long>{

	List<ModuloSistema> findByUnidade_id(Long id);

}

package br.com.portalCrc.repository.chamado;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoTi;

public interface ChamadoTiRepository extends JpaRepository<ChamadoTi, Long>{

	
	@Query("FROM ChamadoTi chamado WHERE chamado.usuarioSolicitante = :usuario")
	Collection<ChamadoTi> listaChamadoUsuario(@Param(value = "usuario") Usuario usuario);
}

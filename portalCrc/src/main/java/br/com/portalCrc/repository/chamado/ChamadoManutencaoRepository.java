package br.com.portalCrc.repository.chamado;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoManutencao;

public interface ChamadoManutencaoRepository extends JpaRepository<ChamadoManutencao, Long>{

	@Query("FROM ChamadoManutencao chamado WHERE chamado.usuarioSolicitante = :usuario")
	Collection<ChamadoManutencao> listaChamadoUsuario(@Param(value = "usuario") Usuario usuario);
}

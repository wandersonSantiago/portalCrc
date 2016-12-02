package br.com.portalCrc.repository.chamado;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoManutencao;

public interface ChamadoManutencaoRepository extends JpaRepository<ChamadoManutencao, Long>{

	@Query("FROM ChamadoManutencao chamado WHERE chamado.usuarioSolicitante = :usuario AND chamado.unidade = :unidade")
	Collection<ChamadoManutencao> listaChamadoUsuario(@Param(value = "usuario") Usuario usuario, @Param(value = "unidade") Unidade unidade);

	@Query("FROM ChamadoManutencao chamado WHERE chamado.unidade = :unidade")
	Collection<ChamadoManutencao> listaSuporte(@Param(value="unidade") Unidade unidade);

}

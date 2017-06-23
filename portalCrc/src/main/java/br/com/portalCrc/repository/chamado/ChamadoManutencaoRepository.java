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

	@Query("FROM ChamadoManutencao chamado WHERE chamado.unidade = :unidade AND chamado.status = 'EM_ANDAMENTO' OR chamado.status = 'ABERTO'")
	Collection<ChamadoManutencao> listaSuporte(@Param(value="unidade") Unidade unidade);
	
	@Query("FROM ChamadoManutencao chamado WHERE CAST(CAST(chamado.dataAbertura as date) as string) >= :dataInicial "
			+ "AND CAST(CAST(chamado.dataAbertura as date) as string) <= :dataFinal "
			+ "AND chamado.titulo = :titulo AND chamado.unidade = :id")
	Iterable<ChamadoManutencao> relatorioPorDataETitulo(@Param(value = "dataInicial") String dataInicial,
			@Param(value = "dataFinal") String dataFinal, @Param(value = "titulo") String titulo,
			@Param(value = "id") Long id);

	@Query("FROM ChamadoManutencao chamado WHERE CAST(CAST(chamado.dataAbertura as date) as string) >= :dataInicial "
			+ "AND CAST(CAST(chamado.dataAbertura as date) as string) <= :dataFinal AND chamado.unidade = :id")
	Collection<ChamadoManutencao> relatorio(@Param(value = "dataInicial") String dataInicial,
			@Param(value = "dataFinal") String dataFinal, @Param(value = "id") Long id);

}

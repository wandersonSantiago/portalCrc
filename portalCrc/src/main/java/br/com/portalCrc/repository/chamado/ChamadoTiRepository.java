package br.com.portalCrc.repository.chamado;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.enums.chamado.StatusChamado;

public interface ChamadoTiRepository extends JpaRepository<ChamadoTi, Long> , JpaSpecificationExecutor<ChamadoTi>, QueryDslPredicateExecutor<ChamadoTi>{

	@Query("FROM ChamadoTi chamado WHERE chamado.usuarioSolicitante = :usuario AND chamado.unidade = :unidade")
	Collection<ChamadoTi> listaChamadoUsuario(@Param(value = "usuario") Usuario usuario,
			@Param(value = "unidade") Unidade unidade);

	@Query("FROM ChamadoTi chamado WHERE chamado.unidade = :unidade AND chamado.status = 'EM_ANDAMENTO' "
			+ "OR chamado.status = 'ABERTO'")
	Collection<ChamadoTi> listaSuporte(@Param(value = "unidade") Unidade unidade);

	Page<ChamadoTi> findByUnidade_id(Long id, Pageable pageRequest);

	@Query("FROM ChamadoTi chamado WHERE CAST(CAST(chamado.dataAbertura as date) as string) >= :dataInicial "
			+ "AND CAST(CAST(chamado.dataAbertura as date) as string) <= :dataFinal "
			+ "AND chamado.titulo.id = :idTitulo AND chamado.unidade.id = :id")
	Collection<ChamadoTi> relatorioPorDataETitulo(@Param(value = "dataInicial") String dataInicial,
			@Param(value = "dataFinal") String dataFinal, @Param(value = "idTitulo") Long idTitulo,
			@Param(value = "id") Long id);

	@Query("FROM ChamadoTi chamado WHERE CAST(CAST(chamado.dataAbertura as date) as string) >= :dataInicial "
			+ "AND CAST(CAST(chamado.dataAbertura as date) as string) <= :dataFinal AND chamado.unidade.id = :id")
	Collection<ChamadoTi> relatorio(@Param(value = "dataInicial") String dataInicial,
			@Param(value = "dataFinal") String dataFinal, @Param(value = "id") Long id);

	long countByStatus(StatusChamado aberto);



	int countByStatusAndSilenciar(StatusChamado aberto, boolean b);

	int countByUnidadeAndStatus(Unidade unidadeAtual, StatusChamado aberto);

	
}

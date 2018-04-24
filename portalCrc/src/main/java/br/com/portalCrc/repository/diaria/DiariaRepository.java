package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;

public interface DiariaRepository extends JpaRepository<Diaria, Long>{

	@Query("From Diaria d where d.status = 'ABERTO'")
	List<Diaria> diariasEmAberto();

	@Query("From Diaria d where year(d.dataAbertura) = ?1 And d.unidadeCadastro.id = ?2")
	List<Diaria> findByDataAbertura(int ano, Long id);

	Page<Diaria> findByStatus(StatusDiariaEnum status, Pageable pageRequest);

	Diaria findTop1ByStatusAndUnidadeCadastro_idOrderByIdDesc(StatusDiariaEnum aberto, Long id);


	List<Diaria> findByUnidadeCadastro_idOrderByIdDesc(Long id);
}

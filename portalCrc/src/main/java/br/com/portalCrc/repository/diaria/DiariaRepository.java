package br.com.portalCrc.repository.diaria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.diaria.Diaria;

public interface DiariaRepository extends JpaRepository<Diaria, Long>{

	@Query("From Diaria d where d.status = 'ABERTO'")
	List<Diaria> diariasEmAberto();

	@Query("From Diaria d where year(d.dataAbertura) = ?1 ")
	List<Diaria> findByDataAbertura(int anoAtual);
}

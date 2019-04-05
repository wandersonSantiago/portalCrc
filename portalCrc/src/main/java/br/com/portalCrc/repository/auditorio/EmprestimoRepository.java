package br.com.portalCrc.repository.auditorio;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.auditorio.Emprestimo;
import br.com.portalCrc.entity.chamado.ChamadoManutencao;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	Page<Emprestimo> findByEventoIgnoreCaseContaining(String texto, Pageable page);

	Page<Emprestimo> findById(Long id, Pageable page);

	//criado para receber status
	Iterable<Emprestimo> findByStatusOrderByIdAsc(StatusEmprestimo statusEmprestimo, Long id);


	

	

}

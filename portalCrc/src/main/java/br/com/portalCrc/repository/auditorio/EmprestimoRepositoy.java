package br.com.portalCrc.repository.auditorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.auditorio.Emprestimo;

public interface EmprestimoRepositoy extends JpaRepository<Emprestimo, Long>{
	
	Page<Emprestimo> findByEventoIgnoreCaseContaining(String texto, Pageable page);

	Page<Emprestimo> findById(Long id, Pageable page);

}

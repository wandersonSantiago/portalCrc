package br.com.portalCrc.repository.auditorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.auditorio.Emprestimo;

public interface EmprestimoRepositoy extends JpaRepository<Emprestimo, Long>{
	
	

}

package br.com.portalCrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.FuncionarioUnidade;

public interface FuncionarioUnidadeRepository extends JpaRepository<FuncionarioUnidade, Long>{

	FuncionarioUnidade findTop1ByFuncionario_idOrderByIdDesc(Long id);

}

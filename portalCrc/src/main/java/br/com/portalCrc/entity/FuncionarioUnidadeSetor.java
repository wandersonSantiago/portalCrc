package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="funcionario_unidade_setor", schema="principal")
public class FuncionarioUnidadeSetor extends AbstractPersistable<Long>{

	@OneToOne
	@JoinColumn(name="id_funcionario_unidade")
	private FuncionarioUnidade funcionarioUnidade;
	
	@OneToOne
	@JoinColumn(name="id_setor")
	private Setor setor;

	public FuncionarioUnidade getFuncionarioUnidade() {
		return funcionarioUnidade;
	}

	public void setFuncionarioUnidade(FuncionarioUnidade funcionarioUnidade) {
		this.funcionarioUnidade = funcionarioUnidade;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	
}

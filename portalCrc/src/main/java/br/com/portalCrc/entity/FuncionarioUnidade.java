package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="funcionario_unidade")
public class FuncionarioUnidade extends AbstractPersistable<Long>{

	@OneToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	@OneToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}	
	
}

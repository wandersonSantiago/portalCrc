package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="funcionario_unidade")
public class FuncionarioUnidade extends AbstractPersistable<Long>{

	
	private Funcionario funcionario;
	
	private DadosUnidade dadosUnidade;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public DadosUnidade getDadosUnidade() {
		return dadosUnidade;
	}

	public void setDadosUnidade(DadosUnidade dadosUnidade) {
		this.dadosUnidade = dadosUnidade;
	}
	
	
	
}

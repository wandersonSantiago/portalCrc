package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="funcionario")
public class Funcionario  extends AbstractPersistable<Long>{

	
	@OneToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	
}

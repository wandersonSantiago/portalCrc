package br.com.portalCrc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "funcionario_id_seq", sequenceName = "funcionario_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="funcionario", schema="principal")
public class Funcionario  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_id_seq")
	private Long id;
	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="cargo_atual")
	private Cargo cargoAtual;
	
	@ManyToOne 
	@JoinColumn(name="id_unidade_atual")
	private Unidade unidadeAtual;
	
	
	
	


	public Unidade getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(Unidade unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public Cargo getCargoAtual() {
		return cargoAtual;
	}

	public void setCargoAtual(Cargo cargoAtual) {
		this.cargoAtual = cargoAtual;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
	

}

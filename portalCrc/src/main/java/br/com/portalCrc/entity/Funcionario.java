package br.com.portalCrc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@JoinColumn(name="id_pessoa" , unique = true , nullable = false)
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name="cargo_atual")
	private Cargo cargoAtual;
	
	@ManyToOne 
	@JoinColumn(name="id_unidade_atual")
	private Unidade unidadeAtual;
	
	@ManyToOne 
	@JoinColumn(name="id_setor_atual")
	private Setor setorAtual;
		
	@ManyToOne 
	@JoinColumn(name="id_funcao_atual")
	private Funcao funcaoAtual;
	
	
	@Column(name="data_cadastro")
	private Date dataCadastro;
	


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

	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Funcao getFuncaoAtual() {
		return funcaoAtual;
	}

	public void setFuncaoAtual(Funcao funcaoAtual) {
		this.funcaoAtual = funcaoAtual;
	}

	public Setor getSetorAtual() {
		return setorAtual;
	}

	public void setSetorAtual(Setor setorAtual) {
		this.setorAtual = setorAtual;
	}

	
	
	

}

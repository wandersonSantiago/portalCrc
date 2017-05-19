package br.com.portalCrc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.enums.StatusFuncionario;

@Entity
@SequenceGenerator(name = "funcionario_unidade_id_seq", sequenceName = "funcionario_unidade_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="funcionario_unidade", schema="principal")
public class FuncionarioUnidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_unidade_id_seq")
	private Long id;
	

	@Column(name="ativo")
	private Boolean ativo;	
	@Enumerated(EnumType.STRING)
	private StatusFuncionario status;
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	@OneToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	@Column(name="data_cadastro")
	private Date dataCadastro;
	@ManyToOne
	@JoinColumn(name="usuario_cadastro")
	private Usuario usuarioCadastro;
	@ManyToOne
	@JoinColumn(name="funcao")
	private Funcao funcao;
	@ManyToOne
	@JoinColumn(name="cargo")
	private Cargo cargo;
	@ManyToOne
	@JoinColumn(name="setor")
	private Setor setor;
	
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public StatusFuncionario getStatus() {
		return status;
	}

	public void setStatus(StatusFuncionario status) {
		this.status = status;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}	
	
	
}

package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "conta_funcionario_diaria_id_seq", sequenceName = "conta_funcionario_diaria_id_seq", initialValue = 1, allocationSize = 1)
@Table(name="conta_funcionario_diaria", schema="diaria")
public class ContaFuncionarioDiaria {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_funcionario_diaria_id_seq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name="banco")
	private String banco;
	
	@Column(name="agencia")
	private String agencia;
	
	@Column(name="conta")
	private String conta;
	
	@Column(name="salario_atual")
	private BigDecimal salarioAtual;
	
	@Column(name="indice_ufesp")
	private Integer indiceUfesp;
	
	@Column(name="limiteCemPorCento")
	private Double limiteCemPorCento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public BigDecimal getSalarioAtual() {
		return salarioAtual;
	}

	public void setSalarioAtual(BigDecimal salarioAtual) {
		this.salarioAtual = salarioAtual;
	}

	public Integer getIndiceUfesp() {
		return indiceUfesp;
	}

	public void setIndiceUfesp(Integer indiceUfesp) {
		this.indiceUfesp = indiceUfesp;
	}

	public Double getLimiteCemPorCento() {
		return limiteCemPorCento;
	}

	public void setLimiteCemPorCento(Double limiteCemPorCento) {
		this.limiteCemPorCento = limiteCemPorCento;
	}

	
}
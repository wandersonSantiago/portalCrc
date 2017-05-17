package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;

@Entity
@SequenceGenerator(name = "diaria_id_seq", sequenceName = "diaria_id_seq", initialValue = 1, allocationSize = 1)
@Table(name="diaria", schema="diaria")
public class Diaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diaria_id_seq")
	private Long id;
	
	
	@Column(name="valorUfesp")
	private BigDecimal valorUfesp;
	
	@Temporal(TemporalType.DATE)
	private Date dataAbertura;
	
	@Temporal(TemporalType.DATE)
	private Date dataFechamento;		
	
	@Enumerated(EnumType.STRING)
	private MesDiariaEnum mes;

	@Enumerated(EnumType.STRING)
	private StatusDiariaEnum status;
	
	@JsonIgnore
	@OneToMany(mappedBy="diaria")
	private List<FuncionarioDiaria> funcionarios;	
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_unidade_cadastro")
	private Unidade unidadeCadastro;
	
	@Column(name="observacao")
	private String observacao;
	
	
	
	
	public MesDiariaEnum getMes() {
		return mes;
	}

	public void setMes(MesDiariaEnum mes) {
		this.mes = mes;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Unidade getUnidadeCadastro() {
		return unidadeCadastro;
	}

	public void setUnidadeCadastro(Unidade unidadeCadastro) {
		this.unidadeCadastro = unidadeCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}	

	public StatusDiariaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusDiariaEnum status) {
		this.status = status;
	}

	public List<FuncionarioDiaria> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioDiaria> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public BigDecimal getValorUfesp() {
		return valorUfesp;
	}

	public void setValorUfesp(BigDecimal valorUfesp) {
		this.valorUfesp = valorUfesp;
	}
	

	

}

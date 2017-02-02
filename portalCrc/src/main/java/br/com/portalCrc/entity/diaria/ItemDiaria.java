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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "item_diaria_id_seq", sequenceName = "item_diaria_id_seq", schema="diaria", initialValue = 1, allocationSize = 1)
@Table(name="item_diaria", schema="diaria")
public class ItemDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_diaria_id_seq")
	private Long id;
	
	@Column(name="motivo")
	private String motivo;
	
	@Column(name="destino")
	private String destino;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataDiaria;
	
	@Column(name="valor_diaria")
	private BigDecimal valorDiaria;
	
	@Column(name="valor_passagem")
	private BigDecimal valorPassagem;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	
	@ManyToOne
	@JoinColumn(name="id_diaria")
	private Diaria diaria;
	
	
	
	public Diaria getDiaria() {
		return diaria;
	}

	public void setDiaria(Diaria diaria) {
		this.diaria = diaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataDiaria() {
		return dataDiaria;
	}

	public void setDataDiaria(Date dataDiaria) {
		this.dataDiaria = dataDiaria;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public BigDecimal getValorPassagem() {
		return valorPassagem;
	}

	public void setValorPassagem(BigDecimal valorPassagem) {
		this.valorPassagem = valorPassagem;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	
	
	
}

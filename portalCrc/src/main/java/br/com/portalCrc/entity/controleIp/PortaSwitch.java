package br.com.portalCrc.entity.controleIp;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.controleIp.StatusSwitch;

@Entity
@SequenceGenerator(name = "porta_id_seq", sequenceName = "porta_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="porta_switch", schema="controle_ip")

public class PortaSwitch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porta_id_seq")
	private Long id;
	
	@Column(name="numero", nullable=false)
	private int numero;
	
	@Enumerated(EnumType.STRING)
	private StatusSwitch status;
	
	@Column(name="em_uso")
	private Boolean emUso;
	
	@Column(name="descricao")
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro" ,  nullable=false)
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@JsonIgnore
	@ManyToOne 
	@JoinColumn(name="id_switch")
	private Switch switchs;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public StatusSwitch getStatus() {
		return status;
	}
	public void setStatus(StatusSwitch status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public Switch getSwitchs() {
		return switchs;
	}
	public void setSwitchs(Switch switchs) {
		this.switchs = switchs;
	}
	public Boolean getEmUso() {
		return emUso;
	}
	public void setEmUso(Boolean emUso) {
		this.emUso = emUso;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	
	
}

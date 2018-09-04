package br.com.portalCrc.entity.controleIp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.enums.controleIp.StatusPortaSwitch;

@Entity
@SequenceGenerator(name = "porta_id_seq", sequenceName = "porta_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="porta_switch", schema="controle_ip")

public class PortaSwitch implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porta_id_seq")
	private Long id;
	
	@Column(name="numero", nullable=false)
	private int numero;
	
	@Enumerated(EnumType.STRING)
	private StatusPortaSwitch status;
	
	
	
	@Column(name="descricao")
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro" ,  nullable=false)
	private Date dataCadastro;
	
	
	@Column(name="id_usuario_cadastro")
	private Long usuarioCadastro;
	

	@Column(name="id_unidade")
	private Long unidade;
	
	@JsonIgnore
	@OneToOne 
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
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public Switch getSwitchs() {
		return switchs;
	}
	public void setSwitchs(Switch switchs) {
		this.switchs = switchs;
	}
	
	public Long getUnidade() {
		return unidade;
	}
	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}
	public StatusPortaSwitch getStatus() {
		return status;
	}
	public void setStatus(StatusPortaSwitch status) {
		this.status = status;
	}
	
	
	
}

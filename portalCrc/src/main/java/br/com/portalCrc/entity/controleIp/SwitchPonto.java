package br.com.portalCrc.entity.controleIp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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


@Entity
@SequenceGenerator(name = "switch_ponto_id_seq", sequenceName = "switch_ponto_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="switch_ponto", schema="controle_ip")
public class SwitchPonto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "switch_ponto_id_seq")
	private Long id;
	
	@Column(name="ponto", nullable=false)
	private int ponto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="porta_switch", nullable=false)	
	private PortaSwitch portaSwitch;

	@Column(name="id_unidade")
	private Long unidade;
		

	@Column(name="id_usuario_cadastro")
	private Long usuarioCadastro;
	
	
	public Long getId() {
		return id;	
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPonto() {
		return ponto;
	}
	public void setPonto(int ponto) {
		this.ponto = ponto;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public PortaSwitch getPortaSwitch() {
		return portaSwitch;
	}
	public void setPortaSwitch(PortaSwitch portaSwitch) {
		this.portaSwitch = portaSwitch;
	}
	public Long getUnidade() {
		return unidade;
	}
	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}
	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	
	

}

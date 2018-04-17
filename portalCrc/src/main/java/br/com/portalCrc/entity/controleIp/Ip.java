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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.controleIp.StatusIp;

@Entity

@SequenceGenerator(name = "ip_id_seq", sequenceName = "ip_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="ip", schema="controle_ip")

public class Ip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ip_id_seq") 
	private Long id;
	
	@Column(name="numero", nullable=false) 
	private String numero;
	
	@OneToOne
	@JoinColumn(name="tipo_ip_id")
	private TipoIp tipo;
	
	@Enumerated(EnumType.STRING)
	private StatusIp status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@OneToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public TipoIp getTipo() {
		return tipo;
	}
	public void setTipo(TipoIp tipo) {
		this.tipo = tipo;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
	public StatusIp getStatus() {
		return status;
	}
	public void setStatus(StatusIp status) {
		this.status = status;
	}
	
	
	

}

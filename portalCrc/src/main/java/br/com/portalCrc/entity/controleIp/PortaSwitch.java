package br.com.portalCrc.entity.controleIp;

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

@Entity
@SequenceGenerator(name = "porta_id_seq", sequenceName = "porta_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="porta_switch", schema="controle_ip")

public class PortaSwitch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porta_id_seq")
	private Long id;
	
	@Column(name="numero", nullable=false)
	private int numero;
	@Column(name="status", nullable=false)
	private String status;
	@Column(name="descricao", nullable=false)
	private String descricao;
	private Date datacadastro;
	@ManyToOne 
	@JoinColumn(name="id_switch" , nullable=false)
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	
	
	
}

package br.com.portalCrc.entity.controleIp;

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


@Entity
@SequenceGenerator(name = "switch_ponto_id_seq", sequenceName = "switch_ponto_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="switch_ponto", schema="controle_ip")
public class SwitchPonto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "switch_ponto_id_seq")
	private Long id;
	
	@Column(name="ponto", nullable=false)
	private int ponto;
	private Date datacadastro;
	@OneToOne
	@JoinColumn(name="porta_switch", nullable=false)	
	private PortaSwitch portaSwitch;
	
	
	
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
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	

}

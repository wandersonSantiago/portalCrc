package br.com.portalCrc.entity.controleIp;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity

@SequenceGenerator(name = "switch_id_seq", sequenceName = "switch_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="switch", schema="controle_ip") 

public class Switch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "switch_id_seq")
	private Long id;
	
	@Column(name = "nome", nullable=false)
	private String nome;
	@Column(name = "status", nullable=false)
	private Boolean status;
	private Date datacadastro;
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER) // verificar
	@Column(nullable=false)
	private Collection<PortaSwitch> portaswitch; 
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Collection<PortaSwitch> getPortaswitch() {
		return portaswitch;
	}
	public void setPortaswitch(Collection<PortaSwitch> portaswitch) {
		this.portaswitch = portaswitch;
	}
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	
	
	
	
	

}

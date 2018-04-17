package br.com.portalCrc.entity.controleIp;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.enums.controleIp.StatusSwitch;



@Entity
@SequenceGenerator(name = "switch_id_seq", sequenceName = "switch_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="switch", schema="controle_ip") 

public class Switch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "switch_id_seq")
	private Long id;
	
	@Column(name = "nome", nullable=false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private StatusSwitch status;
	
	@Column(name = "qtd_portas", nullable=false)
	private int qtdPortas;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro", nullable=false)
	private Date dataCadastro;
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@Column(name="porta_switch", nullable=false)
	private Collection<PortaSwitch> portaSwitch; 
	
	@OneToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	
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
		this.nome = nome.toUpperCase();
	}
	
	public StatusSwitch getStatus() {
		return status;
	}
	public void setStatus(StatusSwitch status) {
		this.status = status;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Collection<PortaSwitch> getPortaSwitch() {
		return portaSwitch;
	}
	public void setPortaSwitch(Collection<PortaSwitch> portaSwitch) {
		this.portaSwitch = portaSwitch;
	}
	public int getQtdPortas() {
		return qtdPortas;
	}
	public void setQtdPortas(int qtdPortas) {
		this.qtdPortas = qtdPortas;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}	
	
}

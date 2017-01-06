package br.com.portalCrc.entity.controleIp;

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

@SequenceGenerator(name = "ip_id_seq", sequenceName = "ip_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) //cria uma sequencia do id automaticamente
@Table(name="ip", schema="controle_ip") // nome da tabela

public class Ip {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ip_id_seq") // indica quem vai gerenciar a sequencia do id
	private Long id;
	@Column(name="numero", nullable=false) 
	private String numero;
	
	@ManyToOne //varios ip recebe um unico tipo
	@JoinColumn(name="tipo_ip_id")
	private TipoIp tipo;
	
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
	
	

}

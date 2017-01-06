package br.com.portalCrc.entity.controleIp;

import javax.persistence.Column;
import javax.persistence.Entity;  //control shift + o serve para buscar as dependencias
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@SequenceGenerator(name = "tipo_ip_id_seq", sequenceName = "tipo_ip_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) //cria uma sequencia do id automaticamente
@Table(name="tipo_ip", schema="controle_ip") // nome da tabela

public class TipoIp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_ip_id_seq") // indica quem vai gerenciar a sequencia do id
	private Long id;
	@Column(name = "descricao", nullable=false)//campo nao vai ser nulo
	private String descricao; //atributos da classe
	
	// get=busca e set=seta objeto para ter acesso aos atributos da classe
	public Long getId() {
		return id;
	}
	
	// void não tem retorno 
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
	

}

package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "secretaria_id_seq", sequenceName = "secretaria_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="secretaria", schema="principal")
public class Secretaria {

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secretaria_id_seq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	@Column(name="mnemonico")
	private String mnemonico;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMnemonico() {
		return mnemonico;
	}
	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
				
	
	
}

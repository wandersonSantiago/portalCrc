package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "pais_id_seq", sequenceName = "pais_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="pais", schema="principal")
public class Pais {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_id_seq")
	private Long id;

	private String nome;

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
	
	
	
}

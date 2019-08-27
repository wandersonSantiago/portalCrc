package br.com.portalCrc.entity.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "marca_id_seq", sequenceName = "marca_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="marca", schema="principal")
public class Marca {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marca_id_seq")
	private Long id;
	
	@Column(name="nome")
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
		this.nome = nome.toUpperCase();
	}

	
}

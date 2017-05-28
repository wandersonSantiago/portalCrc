package br.com.portalCrc.entity.infra;

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
@SequenceGenerator(name = "modelo_id_seq", sequenceName = "modelo_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="modelo", schema="principal")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modelo_id_seq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;

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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	
	
}

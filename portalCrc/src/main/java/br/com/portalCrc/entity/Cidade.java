package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "cidade_id_seq", sequenceName = "cidade_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="cidade", schema="principal")
public class Cidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_id_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	
}

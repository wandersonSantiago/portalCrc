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
@SequenceGenerator(name = "estado_id_seq", sequenceName = "estado_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="estado", schema="principal")
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_pais")
	private Pais pais;
	
	private String nome;
	
	private String sigla;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
}

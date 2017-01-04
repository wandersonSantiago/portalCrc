package br.com.portalCrc.entity;


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
@SequenceGenerator(name = "coordenadoria_id_seq", sequenceName = "coordenadoria_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="coordenadoria", schema="principal")
public class Coordenadoria{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coordenadoria_id_seq")
	private Long id;

	@Column(name="nome")
	private String nome;
	@Column(name="mnemonico")
	private String mnemonico;
	@ManyToOne
	@JoinColumn(name="id_secretaria", nullable = false )
	private Secretaria secretaria;
		
	
	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

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

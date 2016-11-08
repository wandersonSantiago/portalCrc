package br.com.portalCrc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="coordenadoria")
public class Coordenadoria extends AbstractPersistable<Long> {

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

	
	
	
}

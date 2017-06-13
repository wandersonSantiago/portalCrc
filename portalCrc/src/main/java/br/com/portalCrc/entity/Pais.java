package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_pais", schema="comum")
public class Pais {
	
	@Id
	@Column(name="pais_id")
	private Integer id;

	@Column(name="pai_nome")
	private String nome;

	@Column(name="pai_iso", columnDefinition= "bpchar")
	private String sigla;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

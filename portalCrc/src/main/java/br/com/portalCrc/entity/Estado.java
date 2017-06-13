package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_estado" , schema="comum")
public class Estado {
	@Id
	@Column(name="esta_id")
	private Integer id;
	@Column(name="esta_estado")
	private String nome;
	@Column(name="esta_sigla")
	private String sigla;
	@ManyToOne
	@JoinColumn(name="esta_codpais")
	private Pais pais;
	
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
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
}

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
@SequenceGenerator(name = "lista_ramal_id_seq", sequenceName = "lista_ramal_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="lista_ramal", schema="principal")
public class ListaRamal {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lista_ramal_id_seq")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	@Column(name="numero")
	private Integer numero;
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	@ManyToOne
	@JoinColumn(name="id_setor")
	private Setor setor;
	
	
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="lista_ramal", schema="principal")
public class ListaRamal extends AbstractPersistable<Long>{

	
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
	
	
}

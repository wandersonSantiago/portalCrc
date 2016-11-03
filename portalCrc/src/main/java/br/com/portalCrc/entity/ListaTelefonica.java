package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="lista_telefonica")
public class ListaTelefonica extends AbstractPersistable<Long>{

	@Column(name="descricao")
	private String descricao;
	@Column(name="telefone")
	private Integer telefone;
	
	@OneToOne
	@JoinColumn(name="id_setor")
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private DadosUnidade dadosUnidade;
	

	public DadosUnidade getDadosUnidade() {
		return dadosUnidade;
	}
	public void setDadosUnidade(DadosUnidade dadosUnidade) {
		this.dadosUnidade = dadosUnidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	
	
}

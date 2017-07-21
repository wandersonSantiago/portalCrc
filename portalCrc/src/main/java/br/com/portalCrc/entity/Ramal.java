package br.com.portalCrc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@SequenceGenerator(name = "ramal_id_seq", sequenceName = "ramal_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="ramal", schema="principal")
public class Ramal {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ramal_id_seq")
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
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	
	
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
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}

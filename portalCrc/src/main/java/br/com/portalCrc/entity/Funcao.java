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

@Entity
@SequenceGenerator(name = "funcao_id_seq", sequenceName = "funcao_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="funcao", schema="principal")
public class Funcao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcao_id_seq")
	private Long id;

	@Column(name="descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name="id_secretaria")
	private Secretaria secretaria;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}

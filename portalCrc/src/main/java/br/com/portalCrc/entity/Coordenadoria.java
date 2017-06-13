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
	@Column(name="data_cadastro")
	private Date dataCadastro;
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	
	
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
		this.nome = nome.toUpperCase();
	}

	public String getMnemonico() {
		return mnemonico;
	}

	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico.toUpperCase();
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

package br.com.portalCrc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@SequenceGenerator(name = "tipoUnidade_id_seq", sequenceName = "tipoUnidade_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="tipoUnidade", schema="principal")
public class TipoUnidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoUnidade_id_seq")
	private Long id;
	
	@Column(name="descricao" , nullable = false)
	private String descricao;
	@Column(name="mnemonico" , nullable = false)
	private String mnemonico;
	@OneToOne
	@JoinColumn(name="id_secretaria")
	private Secretaria secretaria;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	public Secretaria getSecretaria() {
		return secretaria;
	}
	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
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

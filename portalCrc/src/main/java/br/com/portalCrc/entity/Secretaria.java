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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@SequenceGenerator(name = "secretaria_id_seq", sequenceName = "secretaria_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="secretaria", schema="principal")
public class Secretaria {

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secretaria_id_seq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	@Column(name="mnemonico")
	private String mnemonico;
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	
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
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
				 
	
	
}

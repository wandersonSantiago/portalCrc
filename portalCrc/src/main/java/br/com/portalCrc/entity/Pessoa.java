package br.com.portalCrc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.enums.EstadoCivilEnum;
import br.com.portalCrc.util.ConverteTextoUpperCase;


@Entity
@SequenceGenerator(name = "pessoa_id_seq", sequenceName = "pessoa_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name = "pessoa", schema="principal")
public class Pessoa  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
	private Long id;

	@Column(nullable = false, length = 50)
	private String nomeCompleto;
	@Column(nullable = false, length = 20, unique = true)
	private String rg;
	@Column(nullable = false, length = 20 , unique = true)
	private String cpf;
	@Column(nullable = true, length = 15)
	private String telefoneFixo;
	@Column(nullable = false, length = 15)
	private String telefoneCelular;
	@Column(nullable = true, length = 50)
	private String email;
	@Column(nullable = false)
	private Date dataNascimento;
	@Column(nullable = false)
	private String sexo;
	@Enumerated(EnumType.STRING)
	private EstadoCivilEnum estadaCivil;	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_endereco",nullable = false)
	private Endereco endereco;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = ConverteTextoUpperCase.converte(nomeCompleto);
	}
	
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoCivilEnum getEstadaCivil() {
		return estadaCivil;
	}

	public void setEstadaCivil(EstadoCivilEnum estadaCivil) {
		this.estadaCivil = estadaCivil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	



}

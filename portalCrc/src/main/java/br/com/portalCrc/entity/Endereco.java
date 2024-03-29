package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.util.ConverteTextoUpperCase;

@Entity
@SequenceGenerator(name = "endereco_id_seq", sequenceName = "endereco_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="endereco", schema="principal")
public class Endereco  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
	private Long id;
	
	@Column(nullable = true,length = 50)
	private String logradouro;
	@Column(nullable = true,length = 50)
	private String bairro;
	@Column(nullable = true,length = 50)
	private String localidade;
	@Column(nullable = true)
	private String numero;
	@Column(nullable = true)
	private String uf;
	@Column(nullable = true,length = 9)
	private String cep;
	@Column(nullable = true,length = 50)
	private String complemento;
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro =  ConverteTextoUpperCase.converte(logradouro);
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro =  ConverteTextoUpperCase.converte(bairro);
	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade =  ConverteTextoUpperCase.converte(localidade);
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf.toUpperCase();
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento =  complemento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

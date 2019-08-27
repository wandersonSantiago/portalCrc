package br.com.portalCrc.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.util.ConverteTextoUpperCase;


@Entity
@SequenceGenerator(name = "dadosUnidades_id_seq", sequenceName = "dadosUnidades_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="dadosUnidades", schema="principal")
public class DadosUnidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dadosUnidades_id_seq")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	@Column(name="mnemonico")
	private String mnemonico;
	@Column(name="uge")
	private String uge;
	@Column(name="cnpj")
	private String cnpj;
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_endereco",nullable = false)
	private Endereco endereco;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome =  ConverteTextoUpperCase.converte(nome);
	}

	public String getMnemonico() {
		return mnemonico;
	}
	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getUge() {
		return uge;		
	}
	public void setUge(String uge) {
		this.uge = uge;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
}

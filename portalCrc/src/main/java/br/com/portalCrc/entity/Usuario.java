 package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "usuario")
public class Usuario extends AbstractPersistable<Long>{

	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name="id_dadosUnidades",nullable = true)
	private DadosUnidade dadosUnidade;


	
	@Column(nullable = false,length = 50)
	private String nome;
	@Column(nullable = false,length = 15,unique = false)
	private String login;
	@Column(nullable = true,length = 40)
	private String email;
	@Column(nullable = false,length = 256)
	private String senha;
	@Column(nullable = false)
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public DadosUnidade getDadosUnidade() {
		return dadosUnidade;
	}
	public void setDadosUnidade(DadosUnidade dadosUnidade) {
		this.dadosUnidade = dadosUnidade;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}


	
}
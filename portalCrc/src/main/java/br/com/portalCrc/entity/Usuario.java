 package br.com.portalCrc.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.enums.PerfilUsuario;


@Entity
@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name = "usuario", schema="principal")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;
	@ManyToOne
	@JoinColumn(name="id_setor",nullable = true)
	private Setor setor;
	@ManyToOne
	@JoinColumn(name="id_unidades",nullable = true)
	private Unidade unidade;
	@ElementCollection(targetClass=PerfilUsuario.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="perfil")
    @Column(name="perfil_usuario")
    List<PerfilUsuario> perfilsUsuario;
	@Column(nullable = false,length = 50)
	private String nome;
	@Column(nullable = false,length = 15,unique = true)
	private String login;
	@Column(nullable = true,length = 40)
	private String email;
	@Column(nullable = false,length = 256)
	private String senha;
	
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
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public List<PerfilUsuario> getPerfilsUsuario() {
		return perfilsUsuario;
	}
	public void setPerfilsUsuario(List<PerfilUsuario> perfilsUsuario) {
		this.perfilsUsuario = perfilsUsuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
}

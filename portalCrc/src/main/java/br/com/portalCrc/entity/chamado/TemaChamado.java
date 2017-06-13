package br.com.portalCrc.entity.chamado;

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

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "tema_id_seq", sequenceName = "tema_id_seq", schema="chamado", initialValue = 1, allocationSize = 1)
@Table(name="tema", schema = "chamado")
public class TemaChamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tema_id_seq")
	private Long id;
	
	private String tema;
	
	@ManyToOne
	@JoinColumn(name="id_modulo")
	private ModuloSistema modulo;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	protected Unidade unidade;	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuario;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema.toUpperCase();
	}

	public ModuloSistema getModulo() {
		return modulo;
	}

	public void setModulo(ModuloSistema modulo) {
		this.modulo = modulo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	
}

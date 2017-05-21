package br.com.portalCrc.entity.chamado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.entity.Unidade;

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
		this.tema = tema;
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

	
	
}

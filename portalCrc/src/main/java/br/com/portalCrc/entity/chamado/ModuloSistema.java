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
@SequenceGenerator(name = "modulo_id_seq", sequenceName = "modulo_id_seq", schema="chamado", initialValue = 1, allocationSize = 1)
@Table(name="modulo", schema = "chamado")
public class ModuloSistema {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modulo_id_seq")
	private Long id;
	
	private String descrição;
	
	@ManyToOne
	@JoinColumn(name="id_sistema")
	private Sistema sistema;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	protected Unidade unidade;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	
	
}

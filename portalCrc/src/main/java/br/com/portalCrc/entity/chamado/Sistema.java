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
@SequenceGenerator(name = "sistema_id_seq", sequenceName = "sistema_id_seq", schema="chamado", initialValue = 1, allocationSize = 1)
@Table(name="sistema", schema = "chamado")
public class Sistema {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sistema_id_seq")
	private Long id;
	
	private String descricao;

	@ManyToOne
	@JoinColumn(name="id_unidade")
	protected Unidade unidade;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	
	
}

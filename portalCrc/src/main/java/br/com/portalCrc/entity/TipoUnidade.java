package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="tipoUnidade", schema="principal")
public class TipoUnidade extends AbstractPersistable<Long>{

	@Column(name="descricao" , nullable = false)
	private String descricao;
	@Column(name="mnemonico" , nullable = false)
	private String mnemonico;
	@OneToOne
	@JoinColumn(name="id_secretaria")
	private Secretaria secretaria;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Secretaria getSecretaria() {
		return secretaria;
	}
	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}
	public String getMnemonico() {
		return mnemonico;
	}
	public void setMnemonico(String mnemonico) {
		this.mnemonico = mnemonico;
	}
	
	
	
}

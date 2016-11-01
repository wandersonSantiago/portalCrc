package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="coordenadoria")
public class Coordenadoria extends AbstractPersistable<Long> {

	
	@ManyToOne
	@JoinColumn(name="id_secretaria", nullable = false )
	private Secretaria secretaria;
	
	@OneToOne
	@JoinColumn(name="id_dados_unidade")
	private DadosUnidade dadosUnidade;

	
	
	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}

	public DadosUnidade getDadosUnidade() {
		return dadosUnidade;
	}

	public void setDadosUnidade(DadosUnidade dadosUnidade) {
		this.dadosUnidade = dadosUnidade;
	}
	
	
	
}

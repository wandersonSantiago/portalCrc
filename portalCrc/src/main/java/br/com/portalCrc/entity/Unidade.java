package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.portalCrc.enums.TipoUnidade;

@Entity
@Table(name="unidade")
public class Unidade extends AbstractPersistable<Long> {

		
	@ManyToOne
	@JoinColumn(name="id_coordenadoria")
	private Coordenadoria coordenadoria;
	
	@OneToOne
	@JoinColumn(name="id_dados_unidade")
	private DadosUnidade dadosUnidade;
	
	@Enumerated(EnumType.STRING)
	private TipoUnidade tipoUnidade;

	public Coordenadoria getCoordenadoria() {
		return coordenadoria;
	}

	public void setCoordenadoria(Coordenadoria coordenadoria) {
		this.coordenadoria = coordenadoria;
	}

	public DadosUnidade getDadosUnidade() {
		return dadosUnidade;
	}

	public void setDadosUnidade(DadosUnidade dadosUnidade) {
		this.dadosUnidade = dadosUnidade;
	}

	public TipoUnidade getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(TipoUnidade tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}
	
	
	
	
	
	
	
	
}

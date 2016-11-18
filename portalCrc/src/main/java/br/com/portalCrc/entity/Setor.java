package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.portalCrc.enums.TipoSetorEnum;
import br.com.portalCrc.findControll.View;

@Entity
@Table(name="setor")
public class Setor extends AbstractPersistable<Long> {
	
	
	@Column(name="nome", nullable = false)
	private String nome;
	
	
	@Enumerated(EnumType.STRING)
	private TipoSetorEnum tipoSetor;	
	
	@OneToOne
	@JoinColumn(name="id_tipoUnidade")
	private TipoUnidade tipoUnidade;

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public TipoSetorEnum getTipoSetor() {
		return tipoSetor;
	}


	public void setTipoSetor(TipoSetorEnum tipoSetor) {		
		this.tipoSetor = tipoSetor ;
	}


	public TipoUnidade getTipoUnidade() {
		return tipoUnidade;
	}


	public void setTipoUnidade(TipoUnidade tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}
	
	
}

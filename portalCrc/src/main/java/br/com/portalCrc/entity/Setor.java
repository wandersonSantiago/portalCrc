package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.portalCrc.enums.TipoSetorEnum;

@Entity
@Table(name="setor")
public class Setor extends AbstractPersistable<Long> {

	@Column(name="nome", nullable = false)
	private String nome;
	@Enumerated
	private TipoSetorEnum tipoSetor;


	
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
		this.tipoSetor = tipoSetor;
	}
	
	
}

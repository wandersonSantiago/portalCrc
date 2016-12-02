package br.com.portalCrc.entity.chamado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="chamado_ti", schema="chamado")
public class ChamadoTi extends Chamado {

	
	
	@Column(name="descricao_servico")
	protected String descricaoServico;

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	
	
	
}

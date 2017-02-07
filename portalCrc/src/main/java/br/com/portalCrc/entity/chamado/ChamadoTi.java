package br.com.portalCrc.entity.chamado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;

@Entity
@Table(name="chamado_ti", schema="chamado")
public class ChamadoTi extends Chamado {

	
	
	@Column(name="descricao_servico")
	private String descricaoServico;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoEnum tipoEquipamento;

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public TipoEquipamentoEnum getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamentoEnum tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	
	
	
}

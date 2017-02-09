package br.com.portalCrc.entity.chamado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.enums.chamado.TipoEquipamentoChamadoTi;

@Entity
@Table(name="chamado_ti", schema="chamado")
public class ChamadoTi extends Chamado {

	
	
	@Column(name="descricao_servico")
	private String descricaoServico;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoChamadoTi tipoEquipamento;
	
	@OneToOne
	@JoinColumn(name="id_equipamento")
	private Equipamento equipamento;
	
	
	

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public TipoEquipamentoChamadoTi getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamentoChamadoTi tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	
	
	
}

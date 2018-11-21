package br.com.portalCrc.entity.chamado;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.entity.controleIp.ServicosEquipamento;
import br.com.portalCrc.enums.chamado.TipoEquipamentoChamado;

@Entity
@Table(name="chamado_ti", schema="chamado")
public class ChamadoTi extends Chamado {

	
	
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoChamado tipoEquipamento;
	
	@Column(name="imagem", columnDefinition="text")
	private String imagem;
	
	@OneToOne
	@JoinColumn(name="id_equipamento")
	private Equipamento equipamento;
	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_servicos")
	private ServicosEquipamento servicos;
	
	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public TipoEquipamentoChamado getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamentoChamado tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public ServicosEquipamento getServicos() {
		return servicos;
	}

	public void setServicos(ServicosEquipamento servicos) {
		this.servicos = servicos;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getImagem() {
		return imagem;
	}
	
	public String getImagemBase64() {
		return imagem = "data:image/jpeg;base64," + imagem;
	}
}

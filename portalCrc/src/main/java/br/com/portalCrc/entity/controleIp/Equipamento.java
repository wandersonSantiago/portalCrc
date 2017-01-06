package br.com.portalCrc.entity.controleIp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;



@Entity
@SequenceGenerator(name = "equipamento_id_seq", sequenceName = "equipamento", schema="controle_ip", initialValue = 1, allocationSize =1 )
@Table(name="equipamento", schema="controle_ip")

public class Equipamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_id_seq")
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name="ip", nullable=false)
	private Ip ip;
	@Column(name="nome", nullable=false)
	private String nome;
	@Column(name="patrimonio", nullable=false)
	private String patrimonio;
	@OneToOne
	@JoinColumn(name="ponto", nullable=false)
	private Ponto ponto;
	@ManyToOne
	@JoinColumn(name="unidade", nullable=false)
	private Unidade unidade;
	private Date datacadastro;
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoEnum TipoEquipamento;
	public Long getId() {
		return id;
		
		
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Ip getIp() {
		return ip;
	}
	public void setIp(Ip ip) {
		this.ip = ip;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
	public Ponto getPonto() {
		return ponto;
	}
	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public TipoEquipamentoEnum getTipoEquipamento() {
		return TipoEquipamento;
	}
	public void setTipoEquipamento(TipoEquipamentoEnum tipoEquipamento) {
		TipoEquipamento = tipoEquipamento;
	}
	public Date getDatacadastro() {
		return datacadastro;
	}
	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}
	
	
	
	
	

}

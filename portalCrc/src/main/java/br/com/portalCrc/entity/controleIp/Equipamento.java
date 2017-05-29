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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.controleIp.StatusEquipamento;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;



@Entity
@SequenceGenerator(name = "equipamento_id_seq", sequenceName = "equipamento", schema="controle_ip", initialValue = 1, allocationSize =1 )
@Table(name="equipamento", schema="controle_ip")

public class Equipamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipamento_id_seq")
	private Long id;
	
	
	@OneToOne
	@JoinColumn(name="ip", unique = true)
	private Ip ip;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="patrimonio", unique = true)
	private String patrimonio;
	
	@OneToOne
	@JoinColumn(name="ponto")
	private Ponto ponto;
	
	@ManyToOne
	@JoinColumn(name="unidade", nullable=false)
	private Unidade unidade;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro", nullable=false)
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro", nullable=false)
	private Usuario usuarioCadastro;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoEnum TipoEquipamento;
	
	@Enumerated(EnumType.STRING)
	private StatusEquipamento status;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	public String getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio.toUpperCase();
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
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public StatusEquipamento getStatus() {
		return status;
	}
	public void setStatus(StatusEquipamento status) {
		this.status = status;
	}
	
	
	
	

}

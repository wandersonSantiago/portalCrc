package br.com.portalCrc.entity.controleIp;

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

import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.controleIp.StatusPonto;

@Entity
@SequenceGenerator(name = "ponto_id_seq", sequenceName = "ponto_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="ponto", schema="controle_ip")
public class Ponto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ponto_id_seq")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="numero", nullable=false)
	private String numero;
	
	@ManyToOne
	@JoinColumn(name="setor", nullable=false)
	private Setor setor;
	
	@Enumerated(EnumType.STRING)
	private StatusPonto status;
	
	@OneToOne
	@JoinColumn(name="porta_switch")
	private PortaSwitch portaSwitch;
	
	@OneToOne
	@JoinColumn(name="switch")
	private Switch switchs;
	
	@ManyToOne
	@JoinColumn(name="id_Unidade" )
	private Unidade unidade;
	
	@ManyToOne 
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero.toUpperCase();
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public PortaSwitch getPortaSwitch() {
		return portaSwitch;
	}
	public void setPortaSwitch(PortaSwitch portaSwitch) {
		this.portaSwitch = portaSwitch;
	}
	public Switch getSwitchs() {
		return switchs;
	}
	public void setSwitchs(Switch switchs) {
		this.switchs = switchs;
	}
	public StatusPonto getStatus() {
		return status;
	}
	public void setStatus(StatusPonto status) {
		this.status = status;
	}
	
	

}

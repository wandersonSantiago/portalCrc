package br.com.portalCrc.entity.controleIp;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "manutencao_equipamento_id_seq", sequenceName = "manutencao_equipamento", schema="controle_ip", initialValue = 1, allocationSize =1 )
@Table(name="manutencao_equipamento", schema="controle_ip")
public class ManutencaoEquipamento {

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manutencao_equipamento_id_seq")
	private Long id;
	
	
	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="equipamento", nullable=false)
	private Equipamento equipamento;

	
	@Column(name="data_utilma_manutencao")
	private Date dataUltimaManutencao;
	
	@Column(name="data_preventiva")
	private Date dataPreventiva;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	private Usuario tecnico;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_servicos")
	private ServicosEquipamento servicos;
	
	@Column(name="status")
	private Boolean status;

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
		this.descricao = descricao;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public Date getDataUltimaManutencao() {
		return dataUltimaManutencao;
	}

	public void setDataUltimaManutencao(Date dataUltimaManutencao) {
		this.dataUltimaManutencao = dataUltimaManutencao;
	}

	public Date getDataPreventiva() {
		return dataPreventiva;
	}

	public void setDataPreventiva(Date dataPreventiva) {
		this.dataPreventiva = dataPreventiva;
	}

	public Usuario getTecnico() {
		return tecnico;
	}

	public void setTecnico(Usuario tecnico) {
		this.tecnico = tecnico;
	}

	public ServicosEquipamento getServicos() {
		return servicos;
	}

	public void setServicos(ServicosEquipamento servicos) {
		this.servicos = servicos;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	
	
	
}

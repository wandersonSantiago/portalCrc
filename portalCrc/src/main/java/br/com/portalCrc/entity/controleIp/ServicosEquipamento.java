package br.com.portalCrc.entity.controleIp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "servicos_equipamento_id_seq", sequenceName = "servicos_equipamento", schema="controle_ip", initialValue = 1, allocationSize =1 )
@Table(name="servicos_equipamento", schema="controle_ip")
public class ServicosEquipamento {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servicos_equipamento_id_seq")
	private Long id;
	
	
	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="equipamento", nullable=false)
	private Equipamento equipamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico")
	private Usuario tecnico;
	
	
	
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getTecnico() {
		return tecnico;
	}

	public void setTecnico(Usuario tecnico) {
		this.tecnico = tecnico;
	}
	
	
	
	
}

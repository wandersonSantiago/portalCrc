package br.com.portalCrc.entity;

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


@Entity
@SequenceGenerator(name = "unidade_id_seq", sequenceName = "unidade_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="unidade", schema="principal")
public class Unidade  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_id_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_coordenadoria")
	private Coordenadoria coordenadoria;
	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_dados_unidade")
	private DadosUnidade dadosUnidade;
	
	@OneToOne
	@JoinColumn(name="id_tipoUnidade")
	private TipoUnidade tipoUnidade;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	
	
	
	public Coordenadoria getCoordenadoria() {
		return coordenadoria;
	}

	public void setCoordenadoria(Coordenadoria coordenadoria) {
		this.coordenadoria = coordenadoria;
	}

	public DadosUnidade getDadosUnidade() {
		return dadosUnidade;
	}

	public void setDadosUnidade(DadosUnidade dadosUnidade) {
		this.dadosUnidade = dadosUnidade;
	}

	public TipoUnidade getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(TipoUnidade tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	
	
	
	
	
}

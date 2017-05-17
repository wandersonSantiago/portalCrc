package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "item_diaria_id_seq", sequenceName = "item_diaria_id_seq", schema="diaria", initialValue = 1, allocationSize = 1)
@Table(name="item_diaria", schema="diaria")
public class ItemDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_diaria_id_seq")
	private Long id;
	
	
	@Column(name="localDeslocamento")
	private String localDeslocamento;
	
	@Column(name="codigoLocalDeslocamento")
	private Integer codigoLocalDeslocamento;
	
	@Column(name="meioTransporte")
	private String meioTransporte;
	
	@Column(name="dataHoraSaida")
	private Date dataHoraSaida;
	
	@Column(name="dataHoraChegada")
	private Date dataHoraChegada;		
			
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name="valor_diaria")
	private BigDecimal valorDiaria;
	
	@Column(name="valor_passagem")
	private BigDecimal valorPassagem;
	
	@Column(name="motivo")
	private String motivo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_funcionario_diaria")
	private FuncionarioDiaria funcionarioDiaria;
	
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public BigDecimal getValorPassagem() {
		return valorPassagem;
	}

	public void setValorPassagem(BigDecimal valorPassagem) {
		this.valorPassagem = valorPassagem;
	}

	public FuncionarioDiaria getFuncionarioDiaria() {
		return funcionarioDiaria;
	}

	public void setFuncionarioDiaria(FuncionarioDiaria funcionarioDiaria) {
		this.funcionarioDiaria = funcionarioDiaria;
	}

	public String getLocalDeslocamento() {
		return localDeslocamento;
	}

	public void setLocalDeslocamento(String localDeslocamento) {
		this.localDeslocamento = localDeslocamento;
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte;
	}

	public Date getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(Date dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public Date getDataHoraChegada() {
		return dataHoraChegada;
	}

	public void setDataHoraChegada(Date dataHoraChegada) {
		this.dataHoraChegada = dataHoraChegada;
	}

	public Integer getCodigoLocalDeslocamento() {
		return codigoLocalDeslocamento;
	}

	public void setCodigoLocalDeslocamento(Integer codigoLocalDeslocamento) {
		this.codigoLocalDeslocamento = codigoLocalDeslocamento;
	}

	
	
	
}

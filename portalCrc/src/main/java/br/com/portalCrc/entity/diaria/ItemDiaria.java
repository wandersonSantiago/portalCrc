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

@Entity
@SequenceGenerator(name = "item_diaria_id_seq", sequenceName = "item_diaria_id_seq", schema="diaria", initialValue = 1, allocationSize = 1)
@Table(name="item_diaria", schema="diaria")
public class ItemDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_diaria_id_seq")
	private Long id;
	
	
	@Column(name="local_deslocamento")
	private String localDeslocamento;
	
	@ManyToOne
	@JoinColumn(name="codigo_local_deslocamento")
	private ValoresDiariaLocalidade codigoLocalDeslocamento;
	
	@Column(name="meio_transporte")
	private String meioTransporte;
	
	@Column(name="data_saida")
	private Date dataSaida;
	
	@Column(name="data_chegada")
	private Date dataChegada;	
	
	@Column(name="hora_saida")
	private Date horaSaida;
	
	@Column(name="hora_chegada")
	private Date horaChegada;
			
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name="valor_diaria")
	private BigDecimal valorDiaria;
	
	@Column(name="valor_passagem")
	private BigDecimal valorPassagem;
	
	@Column(name="obs_passagem")
	private String obsPassagem;
	
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
		this.motivo = motivo.toUpperCase();
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
		this.localDeslocamento = localDeslocamento.toUpperCase();
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte.toUpperCase();
	}

	

	public String getObsPassagem() {
		return obsPassagem;
	}

	public void setObsPassagem(String obsPassagem) {
		this.obsPassagem = obsPassagem.toUpperCase();
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Date getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(Date horaChegada) {
		this.horaChegada = horaChegada;
	}

	public ValoresDiariaLocalidade getCodigoLocalDeslocamento() {
		return codigoLocalDeslocamento;
	}

	public void setCodigoLocalDeslocamento(ValoresDiariaLocalidade codigoLocalDeslocamento) {
		this.codigoLocalDeslocamento = codigoLocalDeslocamento;
	}

	

	
	
	
}

package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.portalCrc.entity.Cidade;
import br.com.portalCrc.entity.Estado;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;

@Entity
@SequenceGenerator(name = "item_diaria_id_seq", sequenceName = "item_diaria_id_seq", schema="diaria", initialValue = 1, allocationSize = 1)
@Table(name="item_diaria", schema="diaria")
public class ItemDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_diaria_id_seq")
	private Long id;
	
		
	@NotNull (message="Local de deslocamento não pode ser nulo")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "item_diaria_Localidade", schema="diaria", joinColumns = @JoinColumn(name = "id_item_diaria"), 
	inverseJoinColumns = @JoinColumn(name = "id_cidade"))	
	private Collection<Cidade> localDeslocamentos;	
	
	@NotNull (message="Estado não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;
	
	@NotNull (message="Código de  deslocamento não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="codigo_local_deslocamento")
	private ValoresDiariaLocalidade codigoLocalDeslocamento;
	
	@NotNull (message="Meio de transporte saida não pode ser nulo")
	@Column(name="meio_transporte_saida")
	private String meioTransporteSaida;
	
	@NotNull (message="Meio de transporte retorno não pode ser nulo")
	@Column(name="meio_transporte_retorno")
	private String meioTransporteRetorno;
	
	@NotNull (message="Data de saída não pode ser nulo")
	@Temporal(TemporalType.DATE)
	private Date dataSaida;
	
	@NotNull (message="Data de retorno não pode ser nulo")
	@Temporal(TemporalType.DATE)
	private Date dataChegada;	
	
	@NotNull (message="Horario de saida não pode ser nulo")
	@Column(name="hora_saida")
	private Date horaSaida;
	
	@NotNull (message="Horario de retorno não pode ser nulo")
	@Column(name="hora_chegada")
	private Date horaChegada;
			
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	
	@NotNull (message="Valor das diaria não pode ser nulo")
	@Column(name="valor_diaria")
	private BigDecimal valorDiaria;
	
	@Column(name="valor_passagem")
	private BigDecimal valorPassagem = new BigDecimal(0);
	
	@Column(name="observacao_passagem")
	private String observacaoPassagem;
	
	@NotNull (message="Motido do deslocamento não pode ser nulo")
	@Column(name="motivo")
	private String motivo;
	
	@NotNull (message="Funcionario não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="id_funcionario_diaria")
	private FuncionarioDiaria funcionarioDiaria;
	
	@Column(name="analizado")
	private boolean analizado;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_alteracao")
	private Usuario usuarioAlteracao;

	@Enumerated(EnumType.STRING)
	private TipoDiariaEnum tipo;
	
	
	

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
	
	
	public Collection<Cidade> getLocalDeslocamentos() {
		return localDeslocamentos;
	}

	public void setLocalDeslocamentos(Collection<Cidade> localDeslocamentos) {
		this.localDeslocamentos = localDeslocamentos;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getMeioTransporteSaida() {
		return meioTransporteSaida;
	}

	public void setMeioTransporteSaida(String meioTransporteSaida) {
		this.meioTransporteSaida = meioTransporteSaida;
	}

	public String getMeioTransporteRetorno() {
		return meioTransporteRetorno;
	}

	public void setMeioTransporteRetorno(String meioTransporteRetorno) {
		this.meioTransporteRetorno = meioTransporteRetorno;
	}

	

	public String getObservacaoPassagem() {
		return observacaoPassagem;
	}

	public void setObservacaoPassagem(String observacaoPassagem) {
		this.observacaoPassagem = observacaoPassagem;
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

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public boolean isAnalizado() {
		return analizado;
	}

	public void setAnalizado(boolean analizado) {
		this.analizado = analizado;
	}

	public TipoDiariaEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoDiariaEnum tipo) {
		this.tipo = tipo;
	}
	
	
}

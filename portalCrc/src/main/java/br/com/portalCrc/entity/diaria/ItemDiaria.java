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
import br.com.portalCrc.util.ConverteTextoUpperCase;
import lombok.Data;

@Data
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
		
	@Column(name="meio_transporte_retorno")
	private String meioTransporteRetorno;
	
	@NotNull (message="Data de saída não pode ser nulo")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaida;
	
	@NotNull (message="Data de retorno não pode ser nulo")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataChegada;	
	
	@NotNull (message="Horario de saida não pode ser nulo")
	@Column(name="hora_saida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaSaida;
	
	@NotNull (message="Horario de retorno não pode ser nulo")
	@Column(name="hora_chegada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaChegada;
			
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	
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
	
	@Column(name="retorno")
	private boolean retorno;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_alteracao")
	private Usuario usuarioAlteracao;

	@Enumerated(EnumType.STRING)
	private TipoDiariaEnum tipo;
	
	private Integer qtdPernoite;
	private BigDecimal valorTotalPernoite;
	
	private Integer qtdRegressoTrezeAsDezenove;
	private BigDecimal valorTotalRegressoTrezeAsDezenove;
	
	private Integer qtdRegressoAposDezenove;
	private BigDecimal valorTotalRegressoAposDezenove;
	
	private Integer qtdDeslocamentoDasSeisAsDoze;
	private BigDecimal valorTotalDeslocamentoDasSeisAsDoze;
	
	private Integer qtdDeslocamentoMaisDeDoze;
	private BigDecimal valorDeslocamentoMaisDeDoze;
	
	private Integer qtdAlojamento;
	private BigDecimal valorAlojamento;
	
	
	
	public void setMotivo(String motivo) {
		this.motivo = ConverteTextoUpperCase.converte(motivo);
	}

}

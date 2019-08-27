package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import br.com.portalCrc.entity.Cidade;
import br.com.portalCrc.entity.Estado;
import lombok.Data;

@Data
public class ItemDiariaDTO {


	private Long id;
	
		
	private Collection<Cidade> localDeslocamentos;
	private Estado estado;
	private ValoresDiariaLocalidade codigoLocalDeslocamento;
	private String meioTransporteSaida;
	private String meioTransporteRetorno;
	private Date dataSaida;	
	private Date dataChegada;	
	private Date horaSaida;
	private Date horaChegada;	
	private BigDecimal valorDiaria;	
	private BigDecimal valorPassagem = new BigDecimal(0);	
	private String observacaoPassagem;
	private String motivo;		
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
	
	
	
	public ItemDiariaDTO(ItemDiaria item) {
		this.id = item.getId();
		this.localDeslocamentos = item.getLocalDeslocamentos();
		this.estado = item.getEstado();
		this.codigoLocalDeslocamento = item.getCodigoLocalDeslocamento();
		this.meioTransporteSaida = item.getMeioTransporteSaida();
		this.meioTransporteRetorno = item.getMeioTransporteRetorno();
		this.dataSaida = item.getDataSaida();
		this.dataChegada = item.getDataChegada();
		this.horaSaida = item.getHoraSaida();
		this.horaChegada = item.getHoraChegada();
		this.valorDiaria = item.getValorDiaria();
		this.valorPassagem = item.getValorPassagem();
		this.observacaoPassagem = item.getObservacaoPassagem();
		this.motivo = item.getMotivo();
		this.qtdPernoite = item.getQtdPernoite();
		this.valorTotalPernoite = item.getValorTotalPernoite();
		this.qtdRegressoTrezeAsDezenove = item.getQtdRegressoTrezeAsDezenove();
		this.valorTotalRegressoTrezeAsDezenove = item.getValorTotalRegressoTrezeAsDezenove();
		this.qtdRegressoAposDezenove = item.getQtdRegressoAposDezenove();
		this.valorTotalRegressoAposDezenove = item.getValorTotalRegressoAposDezenove();
		this.qtdDeslocamentoDasSeisAsDoze = item.getQtdDeslocamentoDasSeisAsDoze();
		this.valorTotalDeslocamentoDasSeisAsDoze = item.getValorTotalDeslocamentoDasSeisAsDoze();
		this.qtdDeslocamentoMaisDeDoze = item.getQtdDeslocamentoMaisDeDoze();
		this.valorDeslocamentoMaisDeDoze = item.getValorDeslocamentoMaisDeDoze();
		this.qtdAlojamento = item.getQtdAlojamento();
		this.valorAlojamento = item.getValorAlojamento();
	}
	
	
	
	

}

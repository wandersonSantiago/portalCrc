package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.portalCrc.util.ConverteNumeroEmMoeda;

public class ItemDiariaRelatorioDTO {

	private String localDeslocamentos = "";
	private String codigoLocal =""; 
	
	private String meioTransporte = "";

	private String dataSaida ="";
	private String dataChegada = "";

	private String horaSaida ="";
	private String horaChegada ="";

	private String valorDiaria ="";

	private String motivo = "";
	
	private String qtdPernoite = "";
	private String valorTotalPernoite= "";
	
	private String qtdRegressoTrezeAsDezenove= "";
	private String valorTotalRegressoTrezeAsDezenove= "";
	
	private String qtdRegressoAposDezenove= "";
	private String valorTotalRegressoAposDezenove= "";
	
	private String qtdDeslocamentoDasSeisAsDoze= "";
	private String valorTotalDeslocamentoDasSeisAsDoze= "";
	
	private String qtdDeslocamentoMaisDeDoze= "";
	private String valorDeslocamentoMaisDeDoze= "";
	
	private String qtdAlojamento= "";
	private String valorAlojamento= "";
	
	
	
	

	public ItemDiariaRelatorioDTO(List<ItemDiaria> itens) {
		itens.forEach(iten ->{
		
			SimpleDateFormat sdf = new SimpleDateFormat("dd");
			SimpleDateFormat h = new SimpleDateFormat("HH:mm");
			
			this.codigoLocal += iten.getCodigoLocalDeslocamento().getCodigo().toString() + "\n";
			this.dataSaida += sdf.format(iten.getDataSaida())+ "\n";
			this.dataChegada += sdf.format(iten.getDataChegada()) + "\n";
			this.horaSaida += h.format(iten.getHoraSaida()) +"\n";
			this.horaChegada += h.format(iten.getHoraChegada()) + "\n";
			this.motivo += iten.getMotivo() + "\n";
			this.valorDiaria += ConverteNumeroEmMoeda.real(iten.getValorDiaria()) + "\n";
			
			adicionaCidade(iten);
			adicionaMeioDeTransporte(iten);
			addPernoite(iten);
			addRegressoAposDezenove(iten);
			addRegressoTrezeAsDezenove(iten);
			addDeslocamentoDasSeisAsDoze(iten);
			addDeslocamentoMaisDeDoze(iten);
			
		});
	
	}

	public void addDeslocamentoMaisDeDoze(ItemDiaria iten) {
		if(iten.getQtdDeslocamentoDasSeisAsDoze() != null) {
			this.qtdDeslocamentoMaisDeDoze += iten.getQtdDeslocamentoMaisDeDoze() + "\n";
		}else {
			this.qtdDeslocamentoMaisDeDoze += "\n";
		}
		if(iten.getValorDeslocamentoMaisDeDoze() != null) {
			this.valorDeslocamentoMaisDeDoze += iten.getValorDeslocamentoMaisDeDoze() +"\n";
		}else {
			this.valorDeslocamentoMaisDeDoze += "\n";
		}		
	}
	
	public void addDeslocamentoDasSeisAsDoze(ItemDiaria iten) {
		if(iten.getQtdDeslocamentoDasSeisAsDoze() != null) {
			this.qtdDeslocamentoDasSeisAsDoze += iten.getQtdDeslocamentoDasSeisAsDoze() + "\n";
		}else {
			this.qtdDeslocamentoDasSeisAsDoze += "\n";
		}
		if(iten.getValorTotalDeslocamentoDasSeisAsDoze() != null) {
			this.valorTotalDeslocamentoDasSeisAsDoze += iten.getValorTotalDeslocamentoDasSeisAsDoze() + "\n";
		}else {
			this.valorTotalDeslocamentoDasSeisAsDoze += "\n";
		}		
	}
	
	public void addRegressoTrezeAsDezenove(ItemDiaria iten) {
		if(iten.getQtdRegressoTrezeAsDezenove() != null) {
			this.qtdRegressoTrezeAsDezenove += iten.getQtdRegressoTrezeAsDezenove() + "\n";
		}else {
			this.qtdRegressoTrezeAsDezenove += "\n";
		}
		if(iten.getValorTotalRegressoTrezeAsDezenove() != null) {
			this.valorTotalRegressoTrezeAsDezenove  += iten.getValorTotalRegressoTrezeAsDezenove() + "\n";
		}else {
			this.valorTotalRegressoTrezeAsDezenove += "\n";
		}		
	}
	
	public void addRegressoAposDezenove(ItemDiaria iten) {
		if(iten.getQtdRegressoAposDezenove() != null) {
			this.qtdRegressoAposDezenove += iten.getQtdRegressoAposDezenove() + "\n";
		}else {
			this.qtdRegressoAposDezenove += "\n";
		}
		if(iten.getValorTotalRegressoAposDezenove() != null) {
			this.valorTotalRegressoAposDezenove += iten.getValorTotalRegressoAposDezenove() + "\n";
		}else {
			this.valorTotalRegressoAposDezenove += "\n";
		}		
	}
	
	public void addPernoite(ItemDiaria iten) {
		if(iten.getQtdPernoite() != null) {
			this.qtdPernoite += iten.getQtdPernoite().toString() + "\n";
		}else {
			this.qtdPernoite += "\n";
		}
		if(iten.getValorTotalPernoite() != null) {
			this.valorTotalPernoite += iten.getValorTotalPernoite() + "\n";
		}else {
			this.valorTotalPernoite += "\n";
		}		
	}
	
	
	private String deslocamento = "";
	public void adicionaCidade(ItemDiaria iten) {
		deslocamento = "";
		iten.getLocalDeslocamentos().forEach(local ->{
			String espaco = "";
			if(iten.getLocalDeslocamentos().size() > 1) {
				espaco = "/";
			}
			this.deslocamento += espaco+ local.getNome();
		});
		
		this.localDeslocamentos += this.deslocamento + "\n";
	}

	public void adicionaMeioDeTransporte(ItemDiaria iten) {
		if(iten.getMeioTransporteSaida() != iten.getMeioTransporteRetorno()) {
			this.meioTransporte = iten.getMeioTransporteSaida() + "/" + iten.getMeioTransporteRetorno()  +"\n";
		}else {
			this.meioTransporte += iten.getMeioTransporteSaida() +"\n";
		}
	}



	public String getLocalDeslocamentos() {
		return localDeslocamentos;
	}



	public void setLocalDeslocamentos(String localDeslocamentos) {
		this.localDeslocamentos = localDeslocamentos;
	}

	public String getCodigoLocal() {
		return codigoLocal;
	}

	public void setCodigoLocal(String codigoLocal) {
		this.codigoLocal = codigoLocal;
	}

	public String getMeioTransporte() {
		return meioTransporte;
	}

	public void setMeioTransporte(String meioTransporte) {
		this.meioTransporte = meioTransporte;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(String dataChegada) {
		this.dataChegada = dataChegada;
	}

	public String getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}

	public String getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(String valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDeslocamento() {
		return deslocamento;
	}

	public void setDeslocamento(String deslocamento) {
		this.deslocamento = deslocamento;
	}

	public String getQtdPernoite() {
		return qtdPernoite;
	}

	public void setQtdPernoite(String qtdPernoite) {
		this.qtdPernoite = qtdPernoite;
	}

	public String getValorTotalPernoite() {
		return valorTotalPernoite;
	}

	public void setValorTotalPernoite(String valorTotalPernoite) {
		this.valorTotalPernoite = valorTotalPernoite;
	}

	public String getQtdRegressoTrezeAsDezenove() {
		return qtdRegressoTrezeAsDezenove;
	}

	public void setQtdRegressoTrezeAsDezenove(String qtdRegressoTrezeAsDezenove) {
		this.qtdRegressoTrezeAsDezenove = qtdRegressoTrezeAsDezenove;
	}

	public String getValorTotalRegressoTrezeAsDezenove() {
		return valorTotalRegressoTrezeAsDezenove;
	}

	public void setValorTotalRegressoTrezeAsDezenove(String valorTotalRegressoTrezeAsDezenove) {
		this.valorTotalRegressoTrezeAsDezenove = valorTotalRegressoTrezeAsDezenove;
	}

	public String getQtdRegressoAposDezenove() {
		return qtdRegressoAposDezenove;
	}

	public void setQtdRegressoAposDezenove(String qtdRegressoAposDezenove) {
		this.qtdRegressoAposDezenove = qtdRegressoAposDezenove;
	}

	public String getValorTotalRegressoAposDezenove() {
		return valorTotalRegressoAposDezenove;
	}

	public void setValorTotalRegressoAposDezenove(String valorTotalRegressoAposDezenove) {
		this.valorTotalRegressoAposDezenove = valorTotalRegressoAposDezenove;
	}

	public String getQtdDeslocamentoDasSeisAsDoze() {
		return qtdDeslocamentoDasSeisAsDoze;
	}

	public void setQtdDeslocamentoDasSeisAsDoze(String qtdDeslocamentoDasSeisAsDoze) {
		this.qtdDeslocamentoDasSeisAsDoze = qtdDeslocamentoDasSeisAsDoze;
	}

	public String getValorTotalDeslocamentoDasSeisAsDoze() {
		return valorTotalDeslocamentoDasSeisAsDoze;
	}

	public void setValorTotalDeslocamentoDasSeisAsDoze(String valorTotalDeslocamentoDasSeisAsDoze) {
		this.valorTotalDeslocamentoDasSeisAsDoze = valorTotalDeslocamentoDasSeisAsDoze;
	}

	public String getQtdDeslocamentoMaisDeDoze() {
		return qtdDeslocamentoMaisDeDoze;
	}

	public void setQtdDeslocamentoMaisDeDoze(String qtdDeslocamentoMaisDeDoze) {
		this.qtdDeslocamentoMaisDeDoze = qtdDeslocamentoMaisDeDoze;
	}

	public String getValorDeslocamentoMaisDeDoze() {
		return valorDeslocamentoMaisDeDoze;
	}

	public void setValorDeslocamentoMaisDeDoze(String valorDeslocamentoMaisDeDoze) {
		this.valorDeslocamentoMaisDeDoze = valorDeslocamentoMaisDeDoze;
	}

	public String getQtdAlojamento() {
		return qtdAlojamento;
	}

	public void setQtdAlojamento(String qtdAlojamento) {
		this.qtdAlojamento = qtdAlojamento;
	}

	public String getValorAlojamento() {
		return valorAlojamento;
	}

	public void setValorAlojamento(String valorAlojamento) {
		this.valorAlojamento = valorAlojamento;
	}

	
	


	

	
}

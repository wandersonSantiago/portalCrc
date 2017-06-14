package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;

import br.com.portalCrc.entity.diaria.ItemDiaria;

public class CalculaValor {

	
	
	public BigDecimal verificaHorarioDiaria(ItemDiaria item) {

		DateTime horarioChegada = new DateTime(item.getHoraChegada());
		DateTime horarioSaida = new DateTime(item.getHoraSaida());

		Hours total = Hours.hoursBetween(horarioSaida, horarioChegada);

		total.getHours();

		return valorDiariaPorHorario(total, item);

	}
	

	public BigDecimal valorDiariaPorHorario(Hours totalHoras, ItemDiaria item) {
		BigDecimal totalValor =  new BigDecimal(0);

		if (totalHoras.getHours() >= 12) {
			totalValor = item.getCodigoLocalDeslocamento().getDeslocamentoMaisDeDoze();
		} else if (totalHoras.getHours() >= 6) {
			totalValor = item.getCodigoLocalDeslocamento().getDeslocamentoSeisAsDoze();
		}else{
			throw new MensagemException("Retorno não atigiu o minimo de horas!!!");
		}
		return totalValor;
	}
	

	public Integer quantidadePernoite(ItemDiaria item) {

		DateTime dataFinals = new DateTime(item.getDataChegada());
		DateTime dataInicio = new DateTime(item.getDataSaida());

		Days d = Days.daysBetween(dataInicio, dataFinals);

		return d.getDays();
	}

	
	public BigDecimal valorPernoite(Integer totalPernoite, ItemDiaria item) {

		BigDecimal totalValor = new BigDecimal(0);

		if (totalPernoite > 0) {
			totalValor = item.getCodigoLocalDeslocamento().getPernoite().multiply(new BigDecimal(totalPernoite));
			totalValor  = totalValor.add(verificaHorarioRetorno(item));

		} else if (totalPernoite == 0) {

			totalValor = verificaHorarioDiaria(item);
		}
		return totalValor;
	}

	
	private BigDecimal verificaHorarioRetorno(ItemDiaria item) {
		BigDecimal valorRetorno
		= new BigDecimal(0);
		DateTime dataFinal = new DateTime(item.getHoraChegada());
		int horaChegada = dataFinal.getHourOfDay(); 
		Hours horas = Hours.hours(horaChegada);
		
		if(horas.getHours() >=  19){
			valorRetorno = item.getCodigoLocalDeslocamento().getRetornoAposDezenove();
		}else if(horas.getHours() >= 13){
			valorRetorno = item.getCodigoLocalDeslocamento().getRetornoTrezeAsDezenove();
		}else{
			System.out.println("Retorno não atigiu o minimo de horas!!!");
		}

		return valorRetorno;

	}
}

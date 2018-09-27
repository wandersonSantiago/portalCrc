package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;

import br.com.portalCrc.entity.diaria.ItemDiaria;

public class CalculaValor {

	
	
	public BigDecimal verificaHorarioDiaria(ItemDiaria item) {

		Hours total = Hours.hoursBetween(new DateTime(item.getHoraSaida()), new DateTime(item.getHoraChegada()));

		return valorDiariaPorHorario(total, item);

	}
	

	public BigDecimal valorDiariaPorHorario(Hours totalHoras, ItemDiaria item) {
		BigDecimal totalValor = new BigDecimal(0);

		if (totalHoras.getHours() >= 12) {

			totalValor = item.getCodigoLocalDeslocamento().getDeslocamentoMaisDeDoze();
			item.setQtdDeslocamentoMaisDeDoze(1);
			item.setValorDeslocamentoMaisDeDoze(totalValor);

		} else if (totalHoras.getHours() >= 6) {

			totalValor = item.getCodigoLocalDeslocamento().getDeslocamentoSeisAsDoze();
			item.setQtdDeslocamentoDasSeisAsDoze(1);
			item.setValorTotalDeslocamentoDasSeisAsDoze(totalValor);

		} else {
			throw new MensagemException("Retorno não atigiu o minimo de horas!!!");
		}
		return totalValor;
	}

	
	public Integer quantidadePernoite(ItemDiaria item) {

		Days d = Days.daysBetween(new DateTime(item.getDataSaida()), new DateTime(item.getDataChegada()));		
		
		Hours horas = Hours.hours(new DateTime(item.getHoraChegada()).getHourOfDay());
		
		if(horas.getHours() < 4) {
			d = d.minus(1);
		}

		return d.getDays();
	}

	
	public BigDecimal valorPernoite(Integer totalPernoite, ItemDiaria item) {

		BigDecimal totalValor = new BigDecimal(0);

		if (totalPernoite > 0) {
			totalValor = item.getCodigoLocalDeslocamento().getPernoite().multiply(new BigDecimal(totalPernoite));
			item.setValorTotalPernoite(totalValor);
			totalValor = totalValor.add(verificaHorarioRetorno(item));

		} else if (totalPernoite == 0) {

			if (item.isRetorno()) {
				totalValor = verificaHorarioRetorno(item);
			} else {
				totalValor = verificaHorarioDiaria(item);
			}

		}
		return totalValor;
	}

	
	private BigDecimal verificaHorarioRetorno(ItemDiaria item) {
		BigDecimal valorRetorno = new BigDecimal(0);

		Hours horas = Hours.hours(new DateTime(item.getHoraChegada()).getHourOfDay());

		if (horas.getHours() >= 19 || horas.getHours() <= 3) {

			valorRetorno = item.getCodigoLocalDeslocamento().getRetornoAposDezenove();
			item.setQtdRegressoAposDezenove(1);
			item.setValorTotalRegressoAposDezenove(valorRetorno);
			
		} else if (horas.getHours() >= 13) {

			valorRetorno = item.getCodigoLocalDeslocamento().getRetornoTrezeAsDezenove();
			item.setQtdRegressoTrezeAsDezenove(1);
			item.setValorTotalRegressoTrezeAsDezenove(valorRetorno);

		} else {
			System.out.println("Retorno não atigiu o minimo de horas!!!");
		}

		return valorRetorno;

	}
}

package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Months;
import org.joda.time.Years;

import br.com.portalCrc.entity.diaria.ItemDiaria;

public class CalculaValor {

	public BigDecimal valorPernoite(Integer totalPernoite, ItemDiaria item) {

		BigDecimal total = null;

		if (totalPernoite > 0) {
			total = item.getCodigoLocalDeslocamento().getPernoite().multiply(new BigDecimal(totalPernoite));

			item.setValorDiaria(total);
			
		} else if (totalPernoite == 0) {

		}
		return total;
	}

	public Integer quantidadePernoite(Date dataInicial, Date dataFinal) {
		
		DateTime  dataInicio= new DateTime(dataInicial);
		DateTime dataFinals = new DateTime(dataFinal);
		Days d = Days.daysBetween(dataInicio, dataFinals);
		return d.getDays();
	}
	
	public Integer verificaHorarioRetorno(Date HoraInicial, Date HoraFinal){
		DateTime dataFinal = new DateTime(HoraFinal);
		  DateTime dataInicio = new DateTime(HoraInicial);
		  
		  Hours h = Hours.hoursBetween(dataInicio, dataFinal);
		  
		return h.getHours();
		
	}
	  DateTime dataFinal = new DateTime();
	  DateTime dataInicio = new DateTime(2011, 1, 1, 0, 0);
	   
	  Days d = Days.daysBetween(dataInicio, dataFinal);
	                     
	  Years y = Years.yearsBetween(dataInicio, dataFinal);
	                     
	  Hours h = Hours.hoursBetween(dataInicio, dataFinal);
	                     
	  Months m = Months.monthsBetween(dataInicio, dataFinal);
}

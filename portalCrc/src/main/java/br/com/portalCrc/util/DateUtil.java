package br.com.portalCrc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.portalCrc.service.diaria.MensagemException;

public class DateUtil {
	
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
	
	private DateUtil(){
		
	}
	
	public static Date parse(String str) {
		try {
			return sdf1.parse(str);
		} catch (ParseException e) {
			try {
				return sdf2.parse(str);
			} catch (ParseException e2) {
				throw new MensagemException("Não coseguiu fazer o parse da data");
			}
		}
	}
	
	public static List<Date> extrairDatas(String texto) {
	    List<Date> allMatches = new ArrayList<>();
	    Matcher m = Pattern.compile("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d").matcher(texto);
	    while (m.find()) {
	        allMatches.add(parse(m.group()));
	    }
	    return allMatches;
	}
	
	public static String limparDatas(String texto) {
	    Matcher m = Pattern.compile("(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d\\d").matcher(texto);
	    while (m.find()) {
	    	texto = texto.replace(m.group(),"").trim();
	    }
	    return texto;
	}
	
	public static long diferencaEmDiasEntre(Date dataInicial, Date dataFinal){
		LocalDateTime dtInicial = LocalDateTime.ofInstant(dataInicial.toInstant(), ZoneId.systemDefault());
		LocalDateTime dtFinal = LocalDateTime.ofInstant(dataFinal.toInstant(), ZoneId.systemDefault());
		return ChronoUnit.DAYS.between(dtInicial, dtFinal);
	}
	
	public static Date adicionarAnos(Date data, int qtd){
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.setTime(data);
		dataAtual.add(Calendar.YEAR, 1);		
		dataAtual.getTime();
		return dataAtual.getTime();
	}
	
	
	
	public static Date dataAtual(){
		return new Date();
	}
	
	public static Date getDataHoraAtual() {
		return new GregorianCalendar(Locale.getDefault()).getTime();
	}

	
	public static Integer calculaIdade(Date atual, Date nascimento) {	
		
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataNascimento = Calendar.getInstance();
		
		dataAtual.setTime(atual);
		dataNascimento.setTime(nascimento);		 		
		
		 int idade = dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR); 

		    if (dataAtual.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
		      idade--;  
		    } 
		    else 
		    { 
		        if (dataAtual.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && dataAtual.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
		            idade--; 
		        }
		    }

		return idade;
	}
	
	public static String converteDateEmStringFormatoyyyy(Date data){		
		 SimpleDateFormat formataAno = new SimpleDateFormat("yyyy");		
		 String ano = formataAno.format(data);		 
		 return ano;
	}
	
	public static Date inicioDeAno(Date data){			 
		Calendar date = Calendar.getInstance();
		date.setTime(data);
		date.set(date.get(Calendar.YEAR), 00, 01, 0, 0, 0);
		Date inicioAno = date.getTime();		
		return inicioAno;
	}
	
	public static Date fimDeAno(Date data){		 
		Calendar date = Calendar.getInstance();
		date.setTime(data);
		date.set(date.get(Calendar.YEAR), 11, 31, 23, 59, 59);
		Date fimAno = date.getTime();		
		return fimAno;
	}
	
	public static Date inicioMes(Date data){			 
		Calendar date = Calendar.getInstance();
		date.setTime(data);
		date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 01, 00, 0, 0);
		Date inicioMes = date.getTime();		
		return inicioMes;
	}
	
	public static Date fimMes(Date data){		 
		Calendar date = Calendar.getInstance();
		date.setTime(data);
		date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH), 31, 31, 23, 59);
		Date fimMes = date.getTime();		
		return fimMes;
	}
	
	
	public static Date inicioDia(Date data){			 
		Calendar date = Calendar.getInstance();
		date.setTime(data);
		date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date inicioDia = date.getTime();		
		return inicioDia;
	}
	
	public static Date fimDia(Date data){		 
		Calendar date = Calendar.getInstance();
		date.setTime(data);
		date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		Date fimDia = date.getTime();		
		return fimDia;
	}

	public static Date setaAtributosDate(Date data) {
		Calendar  calendar = Calendar.getInstance();			
		calendar.setTime(data);
		return calendar.getTime();
	}
}

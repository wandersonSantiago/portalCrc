package br.com.portalCrc.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ConverteNumeroEmMoeda {
	
		

 public static String real(BigDecimal valor) {
	 Locale ptBr = new Locale("pt", "BR");
	return NumberFormat.getCurrencyInstance(ptBr).format(valor);
 }

}

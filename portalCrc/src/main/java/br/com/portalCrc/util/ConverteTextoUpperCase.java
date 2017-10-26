package br.com.portalCrc.util;

import org.apache.commons.lang.WordUtils;

public class ConverteTextoUpperCase {
	
	public static String converte(String texto){
		
		try {			
			texto = texto.toLowerCase();
			texto  = WordUtils.capitalize(texto);
			texto = texto.replace("De", "de").replaceAll("Da", "da").replaceAll("Do", "do").replaceAll("Um", "um");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return texto;
	}
	

}

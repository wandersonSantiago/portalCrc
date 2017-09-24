package br.com.portalCrc.util;

public class ConverteTextoUpperCase {
	
	public static String converte(String texto){
		String auxiliar = "";
		try {			
			for(int i = 0; i < texto.length(); ++i){
                if( texto.substring(i, i+1).equals(" "))
                	auxiliar += texto.substring(i+1, i+2).toUpperCase();
                else
                	auxiliar += texto.substring(i+1, i+2).toLowerCase();      
                }
			texto = texto.replace("De", "de").replaceAll("Da", "da").replaceAll("Do", "do");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return texto;
	}
	

}

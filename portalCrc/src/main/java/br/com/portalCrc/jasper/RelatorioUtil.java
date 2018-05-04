package br.com.portalCrc.jasper;

import java.util.HashMap;


public class RelatorioUtil {

	public  String caminhoArquivoDiariaFuncionarios() {
		return "/relatorio/diaria-funcionarios.jrxml";
	}
	
	
	public  HashMap<String, Object> caminhoMapaDeLogos() {

		HashMap<String, Object> hashMap = new HashMap<>();

		hashMap.put("Sap", getClass().getResource("/relatorio/imagens/sap.jpg").toString());
		hashMap.put("Brasao", getClass().getResource("/relatorio/imagens/brasao.jpg").toString());
		
		return hashMap;
	}

}

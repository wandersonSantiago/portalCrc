package br.com.portalCrc.jasper;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RelatorioUtil {
	
	@Value("${caminhoSubRelatorio}")
	private String caminhoSubRelatorios;

	public  String caminhoArquivoDiariaFuncionarios() {
		return "/relatorio/funcionario-diarias-financas.jrxml";
	}
	
	public  String caminhoArquivoDiariaDepartamentoPessoal() {
		return "/relatorio/funcionario-diarias-dep-pessoal.jrxml";
	}
	
	public  HashMap<String, Object> subRelatorioDiariaFinancas() {
		HashMap<String, Object> hashMap = caminhoMapaDeLogos();				
		hashMap.put("SUB_REPORT_DIR",  caminhoSubRelatorios.concat("item-diaria-financas.jrxml"));
		return hashMap;
	}
	
	public  HashMap<String, Object> subRelatorioDiariaDepartamentoPessoal() {
		HashMap<String, Object> hashMap =   caminhoMapaDeLogos();
		hashMap.put("SUB_REPORT_DIR", caminhoSubRelatorios.concat("item-diaria-dep-pessoal.jrxml") );
		return hashMap;
	}
	
	public  HashMap<String, Object> caminhoMapaDeLogos() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("Sap", getClass().getResource("/relatorio/imagens/sap.jpg").toString());
		hashMap.put("Brasao", getClass().getResource("/relatorio/imagens/brasao.jpg").toString());
		return hashMap;
	}

}

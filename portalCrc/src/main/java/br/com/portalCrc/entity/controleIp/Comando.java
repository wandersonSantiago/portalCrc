package br.com.portalCrc.entity.controleIp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comando {
	
	
	
	private String executar;
	private Date data;
	private String host;
	private List<String> retorno = new ArrayList<String>();
	private String ip;
	
	
	
	
	public String getExecutar() {
		return executar;
	}

	public void setExecutar(String executar) {
		this.executar = executar;
	}

	public List<String> getRetorno() {		
		for(String r : retorno) {
			System.out.println(r);
		}
		return retorno;
	}

	public void setRetorno(List<String> retorno) {
		this.retorno = retorno;
	}
	
	public void addRetorno(String texto) {
		this.retorno.add(texto);
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	
	

}

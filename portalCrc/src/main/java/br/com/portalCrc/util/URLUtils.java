package br.com.portalCrc.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;




public class URLUtils {
	
	private Properties propriedades;
	private static URLUtils INSTANCE = new URLUtils();
	private static org.apache.log4j.Logger logger = LogManager.getLogger(URLUtils.class);
	
	
	private URLUtils() {
		propriedades = new Properties();
		try {
			propriedades.load(getClass().getResourceAsStream("/cmd.properties")); 
		}catch (IOException e) {
			logger.error(e);
		} 
	}
	public static URLUtils getInstance(){
		return INSTANCE;
	}
	public static String getChave(String chave){
		return getInstance().getProperty(chave);
	}
	public static String getHost(){
		return getInstance().getProperty("server.host");
	}
	public static String getPort(){
		return getInstance().getProperty("server.port");
	}
	private String getProperty(String key){
		return propriedades.getProperty(key);
	}
	
	public static String getServico(String ip ,String nome){
		return getHost() + ip + getPort() + getChave(nome);
	}
	
}

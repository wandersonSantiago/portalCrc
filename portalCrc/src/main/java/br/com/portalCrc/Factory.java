package br.com.portalCrc;

import java.util.ArrayList;
import java.util.List;

import br.com.portalCrc.entity.diaria.Diaria;
public class Factory {
	
	@SuppressWarnings("deprecation")
	public static List<Diaria> load(){
		
		List<Diaria> diarias = new ArrayList<>();
		return diarias;
	}

}

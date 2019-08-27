package br.com.portalCrc.entity.diaria;

import java.util.List;

import lombok.Data;

@Data
public class ItemDashDTO {

	private String mes;
	private List<Double> valores;
	
	public ItemDashDTO(String mes, List<Double> valores) {
		
		this.mes = mes;
		this.valores = valores;
	}
	
}

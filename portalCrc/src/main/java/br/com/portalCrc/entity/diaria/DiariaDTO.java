package br.com.portalCrc.entity.diaria;

import br.com.portalCrc.util.DateUtil;
import lombok.Data;

@Data
public class DiariaDTO {

	private Long id;
	private String mes;
	
	public DiariaDTO(Diaria diaria) {
		
		this.id = diaria.getId();
		this.mes = diaria.getMes().toString() + "/" + DateUtil.converteDateEmStringFormatoyyyy(diaria.getDataAbertura());
	}
	
	
}

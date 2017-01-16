package br.com.portalCrc.web.controller.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.service.controleIp.EquipamentoService;

@RestController
@RequestMapping("/rest/controleIp/equipamento")
public class EquipamentoRestController {
	
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@PostMapping
	public ResponseEntity<Equipamento> salvarOuAlterar(@RequestBody Equipamento equipamento)
	{
		equipamentoService.salvaOuAltera(equipamento);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);
		
	}

}

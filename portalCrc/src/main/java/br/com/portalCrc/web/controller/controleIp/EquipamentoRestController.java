package br.com.portalCrc.web.controller.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.service.controleIp.EquipamentoService;

@RestController
@RequestMapping("/rest/controleIp/equipamento/")
public class EquipamentoRestController {
	
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@GetMapping
	public ResponseEntity<Iterable<Equipamento>> lista(){
	Iterable<Equipamento> controleIpequipamento	= equipamentoService.lista();
	return new ResponseEntity<Iterable<Equipamento>>(controleIpequipamento, HttpStatus.OK);
		
		}
	
	@PostMapping
	 public ResponseEntity<Equipamento> salvar(@RequestBody Equipamento equipamento,UriComponentsBuilder ucBuilder){
		equipamentoService.salvaOuAltera(equipamento);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Equipamento>(headers, HttpStatus.CREATED);
	 }
		
	
	

}

package br.com.portalCrc.web.controller.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.controleIp.Ponto;
import br.com.portalCrc.service.controleIp.PontoSevice;

@RestController
@RequestMapping("/rest/controleIp/ponto")
public class PontoRestController {

	
	@Autowired
	private PontoSevice pontoSevice; 
	
	@PostMapping
	public ResponseEntity<Ponto> salvarOuAlterar(@RequestBody Ponto ponto)
	{
		
		pontoSevice.salvaOuAltera(ponto);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http, HttpStatus.CREATED);
	
	}
}

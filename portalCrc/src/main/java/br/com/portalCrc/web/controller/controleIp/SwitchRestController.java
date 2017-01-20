package br.com.portalCrc.web.controller.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.controleIp.Switch;
import br.com.portalCrc.service.controleIp.SwitchService;

@RestController
@RequestMapping("/rest/controleIp/switch")
public class SwitchRestController {
		
	@Autowired
	private SwitchService switchService;
	
	@PostMapping
	public ResponseEntity<Switch> salvarOuAlterar(@RequestBody Switch Switch)
	{
		switchService.salvaOuAltera(Switch);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);
		
	}

}
package br.com.portalCrc.web.controller.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.controleIp.Comando;
import br.com.portalCrc.service.controleIp.CMDService;

@RestController
@RequestMapping("/rest/cmd")
public class CMDRestController {
	
	@Autowired
	private CMDService cmdService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/executar")
	public Comando executar(@RequestBody Comando comando) {
		return cmdService.fullexecuter(comando);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/host")
	public Comando host(@RequestBody Comando comando) {
		return cmdService.host(comando);
	}

}

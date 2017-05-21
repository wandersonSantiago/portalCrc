package br.com.portalCrc.web.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.ModuloSistema;
import br.com.portalCrc.service.chamado.ModuloSistemaService;

@RestController
@RequestMapping("/rest/chamado/modulo/sistema")
public class ModuloSistemaRestController {
	
	@Autowired
	private ModuloSistemaService moduloSistemaService;
	
	 @PostMapping
	 public ResponseEntity<ModuloSistema> salvar(@RequestBody ModuloSistema moduloSistema,UriComponentsBuilder ucBuilder){
		 moduloSistemaService.salvarOuEditar(moduloSistema);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ModuloSistema>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping
	 public ResponseEntity<Iterable<ModuloSistema>> listaSuporte() {	  
	  Iterable<ModuloSistema> moduloSistema = moduloSistemaService.findAll();
	  return new ResponseEntity<Iterable<ModuloSistema>>(moduloSistema, HttpStatus.OK);
	 }
}

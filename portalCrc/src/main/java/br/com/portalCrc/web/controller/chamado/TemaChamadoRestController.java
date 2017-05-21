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

import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.service.chamado.TemaChamadoService;

@RestController
@RequestMapping("/rest/chamado/tema")
public class TemaChamadoRestController {

	@Autowired
	private TemaChamadoService temaChamadoService;
	
	 @PostMapping
	 public ResponseEntity<TemaChamado> salvar(@RequestBody TemaChamado temaChamado,UriComponentsBuilder ucBuilder){
		 temaChamadoService.salvarOuEditar(temaChamado);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<TemaChamado>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping
	 public ResponseEntity<Iterable<TemaChamado>> listaSuporte() {	  
	  Iterable<TemaChamado> temaChamado = temaChamadoService.findAll();
	  return new ResponseEntity<Iterable<TemaChamado>>(temaChamado, HttpStatus.OK);
	 }
}

package br.com.portalCrc.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.Secretaria;
import br.com.portalCrc.service.SecretariaService;

@RestController
@RequestMapping("/rest/secretaria")
public class SecretariaRestController {

	
	@Autowired
	private SecretariaService secretariaService;
	
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Secretaria>> lista() {	  
	  Iterable<Secretaria> secretaria = secretariaService.lista();
	  return new ResponseEntity<Iterable<Secretaria>>(secretaria, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Secretaria> salvar(@RequestBody Secretaria secretaria,UriComponentsBuilder ucBuilder){
		 secretariaService.salvarEditar(secretaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Secretaria>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Secretaria> alterar(@RequestBody Secretaria secretaria,UriComponentsBuilder ucBuilder){
		 secretariaService.salvarEditar(secretaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Secretaria>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Secretaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Secretaria>(secretariaService.buscaPorID(id), HttpStatus.OK);
		}
}

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

import br.com.portalCrc.entity.Coordenadoria;
import br.com.portalCrc.service.CoordenadoriaService;

@RestController
@RequestMapping("/rest/coordenadoria")
public class CoordenadoriaRestController {

	@Autowired
	private CoordenadoriaService coordenadoriaService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Coordenadoria>> lista() {	  
	  Iterable<Coordenadoria> coordenadoria = coordenadoriaService.lista();
	  return new ResponseEntity<Iterable<Coordenadoria>>(coordenadoria, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Coordenadoria> salvar(@RequestBody Coordenadoria coordenadoria,UriComponentsBuilder ucBuilder){
		 coordenadoriaService.salvarEditar(coordenadoria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Coordenadoria>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Coordenadoria> alterar(@RequestBody Coordenadoria coordenadoria,UriComponentsBuilder ucBuilder){
		 coordenadoriaService.salvarEditar(coordenadoria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Coordenadoria>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Coordenadoria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Coordenadoria>(coordenadoriaService.buscaPorId(id), HttpStatus.OK);
		}
}

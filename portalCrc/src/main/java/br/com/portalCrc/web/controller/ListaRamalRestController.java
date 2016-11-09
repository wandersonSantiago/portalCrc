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

import br.com.portalCrc.entity.ListaRamal;
import br.com.portalCrc.service.ListaRamalService;

@RestController
@RequestMapping("/rest/ramal")
public class ListaRamalRestController {

	
	@Autowired
	private ListaRamalService listaRamalService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<ListaRamal>> lista() {	  
	  Iterable<ListaRamal> listaRamal = listaRamalService.lista();
	  return new ResponseEntity<Iterable<ListaRamal>>(listaRamal, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<ListaRamal> salvar(@RequestBody ListaRamal listaRamal,UriComponentsBuilder ucBuilder){
		 listaRamalService.salvarEditar(listaRamal);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ListaRamal>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	public ResponseEntity<ListaRamal> alterar(@RequestBody ListaRamal listaRamal,UriComponentsBuilder ucBuilder){
		 listaRamalService.salvarEditar(listaRamal);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ListaRamal>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<ListaRamal> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ListaRamal>(listaRamalService.buscaPorId(id), HttpStatus.OK);
		}

}

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

import br.com.portalCrc.entity.ListaTelefone;
import br.com.portalCrc.service.ListaTelefoneService;


@RestController
@RequestMapping("/rest/telefone")
public class ListaTelefoneRestController {
	
	@Autowired
	private ListaTelefoneService listaTelefoneService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<ListaTelefone>> lista() {	  
	  Iterable<ListaTelefone> listaTelefone = listaTelefoneService.lista();
	  return new ResponseEntity<Iterable<ListaTelefone>>(listaTelefone, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<ListaTelefone> salvar(@RequestBody ListaTelefone listaTelefone,UriComponentsBuilder ucBuilder){
		 listaTelefoneService.salvarEditar(listaTelefone);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ListaTelefone>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	public ResponseEntity<ListaTelefone> alterar(@RequestBody ListaTelefone listaTelefone,UriComponentsBuilder ucBuilder){
		 listaTelefoneService.salvarEditar(listaTelefone);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ListaTelefone>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<ListaTelefone> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ListaTelefone>(listaTelefoneService.buscaPorId(id), HttpStatus.OK);
		}


}

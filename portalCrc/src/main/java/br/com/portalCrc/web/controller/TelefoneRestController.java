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

import br.com.portalCrc.entity.Telefone;
import br.com.portalCrc.service.TelefoneService;


@RestController
@RequestMapping("/rest/telefone")
public class TelefoneRestController {
	
	@Autowired
	private TelefoneService listaTelefoneService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Telefone>> lista() {	  
	  Iterable<Telefone> listaTelefone = listaTelefoneService.lista();
	  return new ResponseEntity<Iterable<Telefone>>(listaTelefone, HttpStatus.OK);
	 } 
	 
	 @RequestMapping(method = RequestMethod.GET, value="/buscarTelefonePorUnidade/{id}")
	 public ResponseEntity<Iterable<Telefone>> buscarTelefonePorUnidade(@PathVariable Long id) {	  
	  Iterable<Telefone> listaTelefone = listaTelefoneService.buscarTelefonePorUnidade(id);
	  return new ResponseEntity<Iterable<Telefone>>(listaTelefone, HttpStatus.OK);
	 } 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Telefone> salvar(@RequestBody Telefone listaTelefone,UriComponentsBuilder ucBuilder){
		 listaTelefoneService.salvarEditar(listaTelefone);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Telefone>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	public ResponseEntity<Telefone> alterar(@RequestBody Telefone listaTelefone,UriComponentsBuilder ucBuilder){
		 listaTelefoneService.salvarEditar(listaTelefone);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Telefone>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Telefone> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Telefone>(listaTelefoneService.buscaPorId(id), HttpStatus.OK);
		}
	 
	


}

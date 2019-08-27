package br.com.portalCrc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.Ramal;
import br.com.portalCrc.service.RamalService;

@RestController
@RequestMapping("/rest/ramal")
public class RamalRestController {

	
	@Autowired
	private RamalService ramalService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Ramal>> lista() {	  
	  Iterable<Ramal> listaRamal = ramalService.lista();
	  return new ResponseEntity<Iterable<Ramal>>(listaRamal, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/buscarRamalPorUnidade/{id}")
	 public ResponseEntity<Iterable<Ramal>> buscarRamalPorUnidade(@PathVariable Long id) {	  
	  Iterable<Ramal> listaRamal = ramalService.buscarRamalPorUnidade(id);
	  return new ResponseEntity<Iterable<Ramal>>(listaRamal, HttpStatus.OK);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?CADASTRAR_RAMAL','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Ramal> salvar(@RequestBody Ramal listaRamal,UriComponentsBuilder ucBuilder){
		 ramalService.salvarEditar(listaRamal);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Ramal>(headers, HttpStatus.CREATED);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?CADASTRAR_RAMAL','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	public ResponseEntity<Ramal> alterar(@RequestBody Ramal listaRamal,UriComponentsBuilder ucBuilder){
		 ramalService.salvarEditar(listaRamal);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Ramal>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Ramal> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Ramal>(ramalService.buscaPorId(id), HttpStatus.OK);
		}

}

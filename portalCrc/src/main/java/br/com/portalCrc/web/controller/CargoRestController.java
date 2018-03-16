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

import br.com.portalCrc.entity.Cargo;
import br.com.portalCrc.service.CargoService;

@RestController
@RequestMapping("/rest/recursosHumanos/cargo")
public class CargoRestController {
	
	@Autowired
	private CargoService cargoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Cargo>> lista() {	  
	  Iterable<Cargo> cargo = cargoService.lista();
	  return new ResponseEntity<Iterable<Cargo>>(cargo, HttpStatus.OK);
	 }
	 
	 @PreAuthorize("hasAnyRole('ROLE_?CARGO','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Cargo> salvar(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder){
		 cargoService.salvarEditar(cargo);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Cargo>(headers, HttpStatus.CREATED);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?CARGO','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Cargo> alterar(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder){
		 cargoService.salvarEditar(cargo);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Cargo>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Cargo> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Cargo>(cargoService.buscaPorId(id), HttpStatus.OK);
		}

}

package br.com.portalCrc.web.controller.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.controleIp.Ponto;
import br.com.portalCrc.enums.controleIp.StatusPonto;
import br.com.portalCrc.service.controleIp.PontoSevice;

@RestController
@RequestMapping("/rest/controleIp/ponto")
public class PontoRestController {

	
	@Autowired
	private PontoSevice pontoSevice; 
	
	@PreAuthorize("hasAnyRole('ROLE_?PONTO','ROLE_?ADMIN')")
	@PostMapping
	public ResponseEntity<Ponto> salvarOuAlterar(@RequestBody Ponto ponto){
		
		pontoSevice.salvaOuAltera(ponto);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http, HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?PONTO','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<Ponto> alterar(@RequestBody Ponto ponto){
		pontoSevice.salvaOuAltera(ponto);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Ponto>> lista(){
		Iterable<Ponto> ponto	= pontoSevice.lista();
		return new ResponseEntity<Iterable<Ponto>>(ponto, HttpStatus.OK);
	}
	@GetMapping(value="/ativos")
	public ResponseEntity<Iterable<Ponto>> listaAtivos(){
		Iterable<Ponto> ponto	= pontoSevice.listaPorStatus(StatusPonto.ATIVO);
		return new ResponseEntity<Iterable<Ponto>>(ponto, HttpStatus.OK);
	}
	@GetMapping(value="inativos")
	public ResponseEntity<Iterable<Ponto>> listaInativos(){
		Iterable<Ponto> ponto	= pontoSevice.listaPorStatus(StatusPonto.INATIVO);
		return new ResponseEntity<Iterable<Ponto>>(ponto, HttpStatus.OK);
	}
		
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<Ponto> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Ponto>(pontoSevice.buscaPorId(id), HttpStatus.OK);
	 }
}

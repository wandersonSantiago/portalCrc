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

import br.com.portalCrc.entity.controleIp.SwitchPonto;
import br.com.portalCrc.service.controleIp.SwitchPontoService;

@RestController
@RequestMapping("/rest/controleIp/switch/ponto")
public class SwitchPontoController {

	
	@Autowired
	private SwitchPontoService swicthPontoService; 
	
	@PreAuthorize("hasAnyRole('ROLE_?SWITCH','ROLE_?ADMIN')")
	@PostMapping
	public ResponseEntity<SwitchPonto> salvarOuAlterar(@RequestBody SwitchPonto ponto){		
		swicthPontoService.salvaOuAltera(ponto);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http, HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?SWITCH','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<SwitchPonto> alterar(@RequestBody SwitchPonto ponto){
		swicthPontoService.salvaOuAltera(ponto);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<SwitchPonto>> lista(){
		Iterable<SwitchPonto> ponto	= swicthPontoService.lista();
		return new ResponseEntity<Iterable<SwitchPonto>>(ponto, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<SwitchPonto> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<SwitchPonto>(swicthPontoService.buscaPorId(id), HttpStatus.OK);
	 }
}

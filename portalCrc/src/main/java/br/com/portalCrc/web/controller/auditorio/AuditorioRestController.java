package br.com.portalCrc.web.controller.auditorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.auditorio.Auditorio;
import br.com.portalCrc.service.auditorio.AuditorioService;

@RestController
@RequestMapping("/rest/agendamento/auditorio")

public class AuditorioRestController {
	
	@Autowired
	private AuditorioService auditorioService;
	
	@GetMapping
	public ResponseEntity<Iterable<Auditorio>> lista(){
		   Iterable<Auditorio> auditorio = auditorioService.lista();
		   return new ResponseEntity<Iterable<Auditorio>>(auditorio, HttpStatus.OK);
		}
	@PostMapping
	public ResponseEntity<Auditorio> salvar (@RequestBody Auditorio auditorio, UriComponentsBuilder ucBuilder) {
		auditorioService.salvarEditar(auditorio);
		HttpHeaders headers =new HttpHeaders();
		return new ResponseEntity<Auditorio>(headers, HttpStatus.CREATED);
					
		
	}  
	
	 @PutMapping
	 public ResponseEntity<Auditorio> alterar(@RequestBody Auditorio auditorio,UriComponentsBuilder ucBuilder){
		 auditorioService.salvarEditar(auditorio);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Auditorio>(headers, HttpStatus.CREATED);
	 }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Auditorio> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<Auditorio>(auditorioService.buscaPorId(id), HttpStatus.OK);
	}
	
}

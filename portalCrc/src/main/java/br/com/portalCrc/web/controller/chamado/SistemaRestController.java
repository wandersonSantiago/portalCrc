package br.com.portalCrc.web.controller.chamado;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.Sistema;
import br.com.portalCrc.service.chamado.SistemaService;

@RestController
@RequestMapping("/rest/informatica/sistema")
public class SistemaRestController {

	@Autowired
	private SistemaService sistemaService;
	
	@PreAuthorize("hasAnyRole('ROLE_?ADMIN')")
	 @PostMapping
	 public ResponseEntity<Sistema> salvar(@RequestBody Sistema sistema,UriComponentsBuilder ucBuilder){
		 sistemaService.salvarOuEditar(sistema);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Sistema>(headers, HttpStatus.CREATED);
	 }
	 
	@PreAuthorize("hasAnyRole('ROLE_?ADMIN')")
	 @PutMapping
	 public ResponseEntity<Sistema> alterar(@RequestBody Sistema sistema,UriComponentsBuilder ucBuilder){
		 sistemaService.salvarOuEditar(sistema);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Sistema>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping
	 public ResponseEntity<Iterable<Sistema>> listaSuporte() {	  
	  Iterable<Sistema> sistema = sistemaService.findAll();
	  return new ResponseEntity<Iterable<Sistema>>(sistema, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/{id}")
		public ResponseEntity<Sistema> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Sistema>(sistemaService.buscaPorId(id), HttpStatus.OK);
		}
}

package br.com.portalCrc.web.controller.chamado;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.ModuloSistema;
import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.service.chamado.ModuloSistemaService;

@RestController
@RequestMapping("/rest/informatica/sistema/modulo")
public class ModuloSistemaRestController {
	
	@Autowired
	private ModuloSistemaService moduloSistemaService;
	
	 @PostMapping
	 public ResponseEntity<ModuloSistema> salvar(@RequestBody ModuloSistema moduloSistema,UriComponentsBuilder ucBuilder){
		 moduloSistemaService.salvarOuEditar(moduloSistema);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ModuloSistema>(headers, HttpStatus.CREATED);
	 }
	 
	 @PutMapping
	 public ResponseEntity<ModuloSistema> alterar(@RequestBody ModuloSistema moduloSistema,UriComponentsBuilder ucBuilder){
		 moduloSistemaService.salvarOuEditar(moduloSistema);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ModuloSistema>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping
	 public ResponseEntity<Iterable<ModuloSistema>> listaSuporte() {	  
	  Iterable<ModuloSistema> moduloSistema = moduloSistemaService.findAll();
	  return new ResponseEntity<Iterable<ModuloSistema>>(moduloSistema, HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/sistema/{idSistema}")
	 public ResponseEntity<Iterable<ModuloSistema>> buscarPorSistema(@PathVariable Long idSistema) {	  
	  Iterable<ModuloSistema> moduloSistema = moduloSistemaService.findBySistema_id(idSistema);
	  return new ResponseEntity<Iterable<ModuloSistema>>(moduloSistema, HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/{id}")
		public ResponseEntity<ModuloSistema> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ModuloSistema>(moduloSistemaService.buscaPorId(id), HttpStatus.OK);
		}
}

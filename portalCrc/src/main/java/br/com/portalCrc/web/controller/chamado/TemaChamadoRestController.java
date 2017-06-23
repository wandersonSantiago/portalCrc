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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.enums.chamado.TipoTema;
import br.com.portalCrc.service.chamado.TemaChamadoService;

@RestController
@RequestMapping("/rest/informatica/sistema/modulo/tema")
public class TemaChamadoRestController {

	@Autowired
	private TemaChamadoService temaChamadoService;
	
	 @PostMapping
	 public ResponseEntity<TemaChamado> salvar(@RequestBody TemaChamado temaChamado,UriComponentsBuilder ucBuilder){
		 temaChamadoService.salvarOuEditar(temaChamado);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<TemaChamado>(headers, HttpStatus.CREATED);
	 }
	 
	 @PutMapping
	 public ResponseEntity<TemaChamado> alterar(@RequestBody TemaChamado temaChamado,UriComponentsBuilder ucBuilder){
		 temaChamadoService.salvarOuEditar(temaChamado);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<TemaChamado>(headers, HttpStatus.CREATED);
	 }
	 

	 @GetMapping
	 public ResponseEntity<Iterable<TemaChamado>> listaSuporte() {	  
	  Iterable<TemaChamado> temaChamado = temaChamadoService.findAll();
	  return new ResponseEntity<Iterable<TemaChamado>>(temaChamado, HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/tema")
	 public ResponseEntity<Iterable<TemaChamado>> buscarPorTema(@RequestParam TipoTema tema) {	  
	  Iterable<TemaChamado> temaChamado = temaChamadoService.findByTipoEquipamento(tema);
	  return new ResponseEntity<Iterable<TemaChamado>>(temaChamado, HttpStatus.OK);
	 }
	 @GetMapping(value="/modulo")
	 public ResponseEntity<Iterable<TemaChamado>> buscarPorModulo(@RequestParam Long idModulo) {	  
	  Iterable<TemaChamado> temaChamado = temaChamadoService.findByModulo_id(idModulo);
	  return new ResponseEntity<Iterable<TemaChamado>>(temaChamado, HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/{id}")
		public ResponseEntity<TemaChamado> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<TemaChamado>(temaChamadoService.buscaPorId(id), HttpStatus.OK);
		}
}

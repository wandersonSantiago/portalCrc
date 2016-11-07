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

import br.com.portalCrc.entity.TipoUnidade;
import br.com.portalCrc.service.TipoUnidadeService;

@RestController
@RequestMapping("/rest/unidade/tipoUnidade")
public class TipoUnidadeRestController {

	
	@Autowired
	private TipoUnidadeService tipoUnidadeService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<TipoUnidade>> lista() {	  
	  Iterable<TipoUnidade> tipoUnidade = tipoUnidadeService.lista();
	  return new ResponseEntity<Iterable<TipoUnidade>>(tipoUnidade, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<TipoUnidade> salvar(@RequestBody TipoUnidade tipoUnidade,UriComponentsBuilder ucBuilder){
		 tipoUnidadeService.salvarEditar(tipoUnidade);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<TipoUnidade>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<TipoUnidade> alterar(@RequestBody TipoUnidade tipoUnidade,UriComponentsBuilder ucBuilder){
		 tipoUnidadeService.salvarEditar(tipoUnidade);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<TipoUnidade>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<TipoUnidade> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<TipoUnidade>(tipoUnidadeService.buscaPorId(id), HttpStatus.OK);
		}
}

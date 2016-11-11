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

import br.com.portalCrc.entity.Funcao;
import br.com.portalCrc.service.FuncaoService;

@RestController
@RequestMapping("/rest/recursosHumanos/funcao")
public class FuncaoRestController {
	
	@Autowired
	private FuncaoService funcaoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Funcao>> lista() {	  
	  Iterable<Funcao> funcao = funcaoService.lista();
	  return new ResponseEntity<Iterable<Funcao>>(funcao, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Funcao> salvar(@RequestBody Funcao funcao,UriComponentsBuilder ucBuilder){
		 funcaoService.salvarEditar(funcao);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Funcao>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Funcao> alterar(@RequestBody Funcao funcao,UriComponentsBuilder ucBuilder){
		 funcaoService.salvarEditar(funcao);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Funcao>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Funcao> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Funcao>(funcaoService.buscaPorId(id), HttpStatus.OK);
		}

}

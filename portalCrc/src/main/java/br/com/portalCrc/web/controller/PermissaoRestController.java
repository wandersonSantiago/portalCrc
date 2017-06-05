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

import br.com.portalCrc.entity.Permissao;
import br.com.portalCrc.service.PermissaoService;




@RestController
@RequestMapping("/rest/permissao")
public class PermissaoRestController {

	@Autowired
	private PermissaoService permissaoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Permissao>> lista() {	  
	  Iterable<Permissao> permissao = permissaoService.lista();
	  return new ResponseEntity<Iterable<Permissao>>(permissao, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Permissao> salvar(@RequestBody Permissao permissao,UriComponentsBuilder ucBuilder){
		 permissaoService.salvarOuEditar(permissao);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Permissao>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Permissao> alterar(@RequestBody Permissao permissao,UriComponentsBuilder ucBuilder){
		 permissaoService.salvarOuEditar(permissao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Permissao>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Permissao> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Permissao>(permissaoService.buscaPorId(id), HttpStatus.OK);
		}
}

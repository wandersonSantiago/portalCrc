package br.com.portalCrc.web.controller;

import java.util.Arrays;

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

import br.com.portalCrc.entity.Permissao;
import br.com.portalCrc.enums.ModuloPermissaoEnum;
import br.com.portalCrc.service.PermissaoService;




@RestController
@RequestMapping("/rest/permissao")
public class PermissaoRestController {

	@Autowired
	private PermissaoService permissaoService;
	
	 @GetMapping
	 public ResponseEntity<Iterable<Permissao>> lista() {	  
	  Iterable<Permissao> permissao = permissaoService.lista();
	  return new ResponseEntity<Iterable<Permissao>>(permissao, HttpStatus.OK);
	 }
	 
	 
	 @PostMapping
	 public ResponseEntity<Permissao> salvar(@RequestBody Permissao permissao,UriComponentsBuilder ucBuilder){
		 permissaoService.salvarOuEditar(permissao);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Permissao>(headers, HttpStatus.CREATED);
	 }

	 @PutMapping
	 public ResponseEntity<Permissao> alterar(@RequestBody Permissao permissao,UriComponentsBuilder ucBuilder){
		 permissaoService.salvarOuEditar(permissao);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Permissao>(headers, HttpStatus.CREATED);
	 }

	 
	 @GetMapping(value = "/{id}")
		public ResponseEntity<Permissao> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Permissao>(permissaoService.buscaPorId(id), HttpStatus.OK);
		}
	 @GetMapping( value = "/modulos")
		public ResponseEntity<Iterable<ModuloPermissaoEnum>> modulos() {
			Iterable<ModuloPermissaoEnum> moduloPermissaoEnum = Arrays.asList(ModuloPermissaoEnum.values());
			return new ResponseEntity<Iterable<ModuloPermissaoEnum>>(moduloPermissaoEnum, HttpStatus.OK);
		}
}

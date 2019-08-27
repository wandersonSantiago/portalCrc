package br.com.portalCrc.web.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.enums.TipoSetorEnum;
import br.com.portalCrc.service.SetorService;

@RestController
@RequestMapping("/rest/recursosHumanos/setor")
public class SetorRestController {
	
	@Autowired
	private SetorService setorService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Collection<Setor>> lista() {	  
	  Collection<Setor> setor = setorService.lista();
	  return new ResponseEntity<Collection<Setor>>(setor, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/lista/unidade")
	 public ResponseEntity<Collection<Setor>> listaPorUnidade() {	  
	  Collection<Setor> setor = setorService.listaPorTipoUnidade();
	  return new ResponseEntity<Collection<Setor>>(setor, HttpStatus.OK);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?SETOR','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Setor> salvar(@RequestBody Setor setor,UriComponentsBuilder ucBuilder){
		 setorService.salvarEditar(setor);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Setor>(headers, HttpStatus.CREATED);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?SETOR','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Setor> alterar(@RequestBody Setor setor,UriComponentsBuilder ucBuilder){
		 setorService.salvarEditar(setor);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Setor>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Setor> buscarUsuarioPorId(@PathVariable Long id) {
			return new ResponseEntity<Setor>(setorService.buscaPorId(id), HttpStatus.OK);
		}
	 
	@RequestMapping(method = RequestMethod.GET, value = "/tipoSetor")
	public ResponseEntity<Iterable<TipoSetorEnum>> tipoSetor() {
		Iterable<TipoSetorEnum> tipoSetor = Arrays.asList(TipoSetorEnum.values());
		return new ResponseEntity<Iterable<TipoSetorEnum>>(tipoSetor, HttpStatus.OK);
	}

}

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

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.service.FuncionarioService;

@RestController
@RequestMapping("/rest/recursosHumanos/funcionario")
public class FuncionarioRestController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Funcionario>> lista() {	  
	  Iterable<Funcionario> funcionario = funcionarioService.lista();
	  return new ResponseEntity<Iterable<Funcionario>>(funcionario, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario,UriComponentsBuilder ucBuilder){
		 funcionarioService.salvarEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Funcionario>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<Funcionario> alterar(@RequestBody Funcionario funcionario,UriComponentsBuilder ucBuilder){
		 funcionarioService.salvarEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Funcionario>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Funcionario>(funcionarioService.buscaPorId(id), HttpStatus.OK);
		}
}

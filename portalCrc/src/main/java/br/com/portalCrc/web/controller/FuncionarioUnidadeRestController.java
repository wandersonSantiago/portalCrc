package br.com.portalCrc.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.FuncionarioUnidade;
import br.com.portalCrc.enums.StatusFuncionario;
import br.com.portalCrc.service.FuncionarioUnidadeService;

@RestController
@RequestMapping("/rest/recursosHumanos/funcionarioUnidade")
public class FuncionarioUnidadeRestController {
	
	@Autowired
	private FuncionarioUnidadeService funcionarioUnidadeService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<FuncionarioUnidade>> lista() {	  
	  Iterable<FuncionarioUnidade> funcionario = funcionarioUnidadeService.lista();
	  return new ResponseEntity<Iterable<FuncionarioUnidade>>(funcionario, HttpStatus.OK);
	 }
	 
	 @PreAuthorize("hasAnyRole('ROLE_?FUNCIONARIO_UNIDADE','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<FuncionarioUnidade> salvar(@RequestBody FuncionarioUnidade funcionario,UriComponentsBuilder ucBuilder){
		 funcionarioUnidadeService.salvarEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<FuncionarioUnidade>(headers, HttpStatus.CREATED);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?FUNCIONARIO_UNIDADE','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	public ResponseEntity<FuncionarioUnidade> alterar(@RequestBody FuncionarioUnidade funcionario,UriComponentsBuilder ucBuilder){
		 funcionarioUnidadeService.salvarEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<FuncionarioUnidade>(headers, HttpStatus.CREATED);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?FUNCIONARIO_UNIDADE','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.PUT, value="/alterar/unidade/{idUnidade}")
		public ResponseEntity<FuncionarioUnidade> alterarUnidade(@PathVariable Long idUnidade){
			 funcionarioUnidadeService.alterarUnidade(idUnidade);
			 HttpHeaders headers =new HttpHeaders();
			 return new ResponseEntity<FuncionarioUnidade>(headers, HttpStatus.CREATED);
		 }
	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<FuncionarioUnidade> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<FuncionarioUnidade>(funcionarioUnidadeService.buscaPorId(id), HttpStatus.OK);
		}
	 @RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
		public ResponseEntity<FuncionarioUnidade> buscarPorIdFuncionario(@PathVariable Long id) {
			return new ResponseEntity<FuncionarioUnidade>(funcionarioUnidadeService.findByFuncionario_idTop1Desc(id), HttpStatus.OK);
		}
	 @RequestMapping(method = RequestMethod.GET, value = "/status")
		public ResponseEntity<Iterable<StatusFuncionario>> status() {
			Iterable<StatusFuncionario> status = Arrays.asList(StatusFuncionario.values());
			return new ResponseEntity<Iterable<StatusFuncionario>>(status, HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/buscar")
		public ResponseEntity<?> buscar(@RequestParam("q")String texto) {
				return new ResponseEntity<List<FuncionarioUnidade>>(funcionarioUnidadeService.buscar(texto), HttpStatus.OK);
		}
}

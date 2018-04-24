package br.com.portalCrc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.service.FuncionarioService;

@RestController
@RequestMapping("/rest/recursosHumanos/funcionario")
public class FuncionarioRestController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	
	 
	 @RequestMapping(method = RequestMethod.GET, value="/lista/porUnidade")
	 public ResponseEntity<Iterable<Funcionario>> listaPorUnidade() {	  
	  Iterable<Funcionario> funcionario = funcionarioService.listaPorUnidade();
	  return new ResponseEntity<Iterable<Funcionario>>(funcionario, HttpStatus.OK);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?FUNCIONARIO_UNIDADE','ROLE_?ADMIN')")
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario,UriComponentsBuilder ucBuilder){
		 funcionarioService.salvarEditar(funcionario);
		 return new ResponseEntity<Funcionario>(funcionario, HttpStatus.CREATED);
	 }
	 @PreAuthorize("hasAnyRole('ROLE_?FUNCIONARIO_UNIDADE','ROLE_?ADMIN')")
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
	 
	 @RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
		public ResponseEntity<Funcionario> verificaCpf(@PathVariable String cpf) {
			return new ResponseEntity<Funcionario>(funcionarioService.verificaCpf(cpf), HttpStatus.OK);
		}
	 
		
	
	 @GetMapping(value = "/descricao")
		public ResponseEntity<Page<Funcionario>> findByDescricao(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="pessoa.nomeCompleto") String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC") String direction,
				@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

			Page<Funcionario> list = null;
			
			if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
				list = funcionarioService.findAll(new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy));
			}else {
				list = funcionarioService.buscar(descricao, new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy));
			}
			
			return ResponseEntity.ok().body(list);
		}
}

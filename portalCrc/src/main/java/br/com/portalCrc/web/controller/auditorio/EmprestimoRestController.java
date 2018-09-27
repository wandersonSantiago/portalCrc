package br.com.portalCrc.web.controller.auditorio;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.auditorio.Emprestimo;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;
import br.com.portalCrc.service.auditorio.EmprestimoService;

@RestController
@RequestMapping("/rest/auditorio/emprestimo")

public class EmprestimoRestController {
	
		
	@Autowired
	private EmprestimoService emprestimoService;
	
	@GetMapping
	public ResponseEntity<Iterable<Emprestimo>> lista(){
		   Iterable<Emprestimo> emprestimo = emprestimoService.lista();
		   return new ResponseEntity<Iterable<Emprestimo>>(emprestimo, HttpStatus.OK);
		}
	
	
	@PostMapping
	public ResponseEntity<Emprestimo> salvar (@RequestBody Emprestimo emprestimo, UriComponentsBuilder ucBuilder) {
		emprestimoService.salvarEditar(emprestimo);
		HttpHeaders headers =new HttpHeaders();
		return new ResponseEntity<Emprestimo>(headers, HttpStatus.CREATED);
			
		
	}  
	
	@RequestMapping(method = RequestMethod.GET, value = "/status")
	public ResponseEntity<Iterable<StatusEmprestimo>> status() {
		Iterable<StatusEmprestimo> statusEmprestimo = Arrays.asList(StatusEmprestimo.values());
		return new ResponseEntity<Iterable<StatusEmprestimo>>(statusEmprestimo, HttpStatus.OK);
	}
	
	@PutMapping
	 public ResponseEntity<Emprestimo> alterar(@RequestBody Emprestimo emprestimo,UriComponentsBuilder ucBuilder){
		 emprestimoService.salvarEditar(emprestimo);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Emprestimo>(headers, HttpStatus.CREATED);
	 }
	
		
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Emprestimo> buscarPorId(@PathVariable Long id) {
		Emprestimo emprestimo = emprestimoService.buscaPorId(id);
		System.out.println(emprestimo);
		return new ResponseEntity<Emprestimo>(emprestimo, HttpStatus.OK);
		}

}


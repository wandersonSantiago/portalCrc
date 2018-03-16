package br.com.portalCrc.web.controller.diaria;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.diaria.ContaFuncionarioDiaria;
import br.com.portalCrc.enums.diaria.IndiceUfespEnum;
import br.com.portalCrc.enums.diaria.LimitePorcentagemSalarioEnum;
import br.com.portalCrc.service.diaria.ContaFuncionarioDiariaService;

@RestController
@RequestMapping("/rest/diaria/funcionario/conta")
public class ContaFuncionarioDiariaRestController {
	
	@Autowired
	private ContaFuncionarioDiariaService contaFuncionarioDiariaService;
	
	@PreAuthorize("hasAnyRole('ROLE_?CONTA_FUNCIONARIO', 'ROLE_?DIARIA_FINANCAS_LANCAMENTO','ROLE_?ADMIN')")
	@PostMapping
	 public ResponseEntity<ContaFuncionarioDiaria> salvar(@RequestBody ContaFuncionarioDiaria contaFuncionarioDiaria, UriComponentsBuilder ucBuilder){
		contaFuncionarioDiariaService.salvar(contaFuncionarioDiaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ContaFuncionarioDiaria>(headers, HttpStatus.CREATED);
	 }
		
	@PreAuthorize("hasAnyRole('ROLE_?CONTA_FUNCIONARIO', 'ROLE_?DIARIA_FINANCAS_LANCAMENTO','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<ContaFuncionarioDiaria> alterar(@RequestBody ContaFuncionarioDiaria contaFuncionarioDiaria, UriComponentsBuilder ucBuilder){
		contaFuncionarioDiariaService.altera(contaFuncionarioDiaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?CONTA_FUNCIONARIO', 'ROLE_?DIARIA_FINANCAS_LANCAMENTO','ROLE_?ADMIN')")
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<ContaFuncionarioDiaria> excluir(@PathVariable Long id){
		contaFuncionarioDiariaService.excluir(id);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping(value="/listaSecretaria/{id}")
	public ResponseEntity<Iterable<ContaFuncionarioDiaria>> listaSecretaria(@PathVariable Long id){
		Iterable<ContaFuncionarioDiaria> contaFuncionarioDiaria	= contaFuncionarioDiariaService.listaSecretaria(id);
		return new ResponseEntity<Iterable<ContaFuncionarioDiaria>>(contaFuncionarioDiaria, HttpStatus.OK);
	}
		
	@GetMapping(value="/listaCoordenadoria/{id}")
	public ResponseEntity<Iterable<ContaFuncionarioDiaria>> listaCoordenadoria(@PathVariable Long id){
		Iterable<ContaFuncionarioDiaria> contaFuncionarioDiaria = contaFuncionarioDiariaService.listaCoordenadoria(id);
		return new ResponseEntity<Iterable<ContaFuncionarioDiaria>>(contaFuncionarioDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/unidade")
	public ResponseEntity<Iterable<ContaFuncionarioDiaria>> listaUnidade(){
		Iterable<ContaFuncionarioDiaria> contaFuncionarioDiaria = contaFuncionarioDiariaService.lista();
		return new ResponseEntity<Iterable<ContaFuncionarioDiaria>>(contaFuncionarioDiaria, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<ContaFuncionarioDiaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ContaFuncionarioDiaria>(contaFuncionarioDiariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/funcionario/{id}")
		public ResponseEntity<ContaFuncionarioDiaria> buscarPorIdFuncionario(@PathVariable Long id) {
			return new ResponseEntity<ContaFuncionarioDiaria>(contaFuncionarioDiariaService.findByFuncionario_id(id), HttpStatus.OK);
	 }
	 @GetMapping(value = "/buscar")
		public ResponseEntity<?> buscar(@RequestParam("q")String texto) {
		 return new ResponseEntity<List<ContaFuncionarioDiaria>>(contaFuncionarioDiariaService.buscar(texto), HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/indice")
		public ResponseEntity<Iterable<IndiceUfespEnum>> indiceEnum() {
			Iterable<IndiceUfespEnum> indiceUfespEnum = Arrays.asList(IndiceUfespEnum.values());
			return new ResponseEntity<Iterable<IndiceUfespEnum>>(indiceUfespEnum, HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/limite")
		public ResponseEntity<Iterable<LimitePorcentagemSalarioEnum>> limiteEnum() {
			Iterable<LimitePorcentagemSalarioEnum> limitePorcentagemSalarioEnum = Arrays.asList(LimitePorcentagemSalarioEnum.values());
			return new ResponseEntity<Iterable<LimitePorcentagemSalarioEnum>>(limitePorcentagemSalarioEnum, HttpStatus.OK);
		}

}

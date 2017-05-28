package br.com.portalCrc.web.controller.diaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;
import br.com.portalCrc.service.diaria.FuncionarioDiariaService;


@RestController
@RequestMapping("/rest/diaria/funcionario")
public class FuncionarioDiariaRestController {

	@Autowired
	private FuncionarioDiariaService funcionarioDiariaService;
	
	@PostMapping
	 public ResponseEntity<FuncionarioDiaria> salvar(@RequestBody FuncionarioDiaria funcionarioDiaria, UriComponentsBuilder ucBuilder){
		funcionarioDiariaService.salvaOuAltera(funcionarioDiaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<FuncionarioDiaria>(headers, HttpStatus.CREATED);
	 }
		
	@PutMapping
	public ResponseEntity<FuncionarioDiaria> alterar(@RequestBody FuncionarioDiaria funcionarioDiaria, UriComponentsBuilder ucBuilder){
		funcionarioDiariaService.salvaOuAltera(funcionarioDiaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<FuncionarioDiaria> excluir(@PathVariable Long id){
		funcionarioDiariaService.excluir(id);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping(value="/listaSecretaria/{id}")
	public ResponseEntity<Iterable<FuncionarioDiaria>> listaSecretaria(@PathVariable Long id){
		Iterable<FuncionarioDiaria> funcionarioDiaria	= funcionarioDiariaService.listaSecretaria(id);
		return new ResponseEntity<Iterable<FuncionarioDiaria>>(funcionarioDiaria, HttpStatus.OK);
	}

	@GetMapping(value="/valores/indice/{indice}/diaria/{idDiaria}")
	public ResponseEntity<Iterable<ValoresDiariaLocalidade>> valoresDiaria(@PathVariable Integer indice, @PathVariable Long idDiaria){
		Iterable<ValoresDiariaLocalidade> funcionarioDiaria	= funcionarioDiariaService.valoresDiaria(indice, idDiaria);
		return new ResponseEntity<Iterable<ValoresDiariaLocalidade>>(funcionarioDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/listaCoordenadoria/{id}")
	public ResponseEntity<Iterable<FuncionarioDiaria>> listaCoordenadoria(@PathVariable Long id){
		Iterable<FuncionarioDiaria> funcionarioDiaria = funcionarioDiariaService.listaCoordenadoria(id);
		return new ResponseEntity<Iterable<FuncionarioDiaria>>(funcionarioDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}/funcionarios")
	public ResponseEntity<Iterable<FuncionarioDiaria>> funcionariosPorDiaria(@PathVariable Long id){
		Iterable<FuncionarioDiaria> funcionarioDiaria = funcionarioDiariaService.findByUnidade_idAndDiaria_id(id);
		return new ResponseEntity<Iterable<FuncionarioDiaria>>(funcionarioDiaria, HttpStatus.OK);
	}
	@GetMapping(value="/listaUnidade/{id}")
	public ResponseEntity<Iterable<FuncionarioDiaria>> listaUnidade(@PathVariable Long id){
		Iterable<FuncionarioDiaria> funcionarioDiaria = funcionarioDiariaService.listaUnidade(id);
		return new ResponseEntity<Iterable<FuncionarioDiaria>>(funcionarioDiaria, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<FuncionarioDiaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<FuncionarioDiaria>(funcionarioDiariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 

}

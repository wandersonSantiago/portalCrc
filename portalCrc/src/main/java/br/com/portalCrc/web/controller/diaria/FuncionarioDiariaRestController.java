package br.com.portalCrc.web.controller.diaria;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.FuncionarioDiariaDTO;
import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;
import br.com.portalCrc.service.diaria.FuncionarioDiariaService;


@RestController
@RequestMapping("/rest/diaria/funcionario")
public class FuncionarioDiariaRestController {

	@Autowired
	private FuncionarioDiariaService funcionarioDiariaService;
	
	@PreAuthorize("hasAnyRole('ROLE_?DIARIA_USUARIO', 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PostMapping
	 public ResponseEntity<FuncionarioDiaria> salvar(@RequestBody FuncionarioDiaria funcionarioDiaria, UriComponentsBuilder ucBuilder){
		funcionarioDiariaService.salvaOuAltera(funcionarioDiaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<FuncionarioDiaria>(headers, HttpStatus.CREATED);
	 }
	
	@PreAuthorize("hasAnyRole('ROLE_?DIARIA_USUARIO', 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<FuncionarioDiaria> alterar(@RequestBody FuncionarioDiaria funcionarioDiaria, UriComponentsBuilder ucBuilder){
		funcionarioDiariaService.salvaOuAltera(funcionarioDiaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?DIARIA_USUARIO', 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<FuncionarioDiaria> excluir(@PathVariable Long id){
		funcionarioDiariaService.excluir(id);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	

	@GetMapping(value="/valores/indice/{indice}/diaria/{idDiaria}")
	public ResponseEntity<Iterable<ValoresDiariaLocalidade>> valoresDiaria(@PathVariable Integer indice, @PathVariable Long idDiaria){
		Iterable<ValoresDiariaLocalidade> funcionarioDiaria	= funcionarioDiariaService.valoresDiaria(indice, idDiaria);
		return new ResponseEntity<Iterable<ValoresDiariaLocalidade>>(funcionarioDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/coordenadoria/{idDiaria}")
	public ResponseEntity<Iterable<FuncionarioDiaria>> listaCoordenadoria(@PathVariable Long idDiaria){
		Iterable<FuncionarioDiaria> funcionarioDiaria = funcionarioDiariaService.listaCoordenadoria(idDiaria);
		return new ResponseEntity<Iterable<FuncionarioDiaria>>(funcionarioDiaria, HttpStatus.OK);
	}
	
  @GetMapping(value="/unidade")
	public ResponseEntity<Page<FuncionarioDiariaDTO>> funcionariosPorDiaria(
			@RequestParam(value="idDiaria", required=true) Long idDiaria,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="contaFuncionario.funcionario.pessoa.nomeCompleto") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="q", required = false , defaultValue="")String texto) {		 
	 
	  Page<FuncionarioDiaria> list = null;
		
		if(texto.isEmpty() || texto.equalsIgnoreCase("")) {
			list = funcionarioDiariaService.findaAllDiariaId(idDiaria, new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = funcionarioDiariaService.findaAllDiariaIdAndTexto(idDiaria, texto, new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		Page<FuncionarioDiariaDTO> listDto = list.map(obj -> new FuncionarioDiariaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/conta/{idFuncionario}/diaria/{idDiaria}")
	public ResponseEntity<FuncionarioDiaria> buscarFuncionario(@PathVariable Long idFuncionario, @PathVariable Long idDiaria){
		FuncionarioDiaria funcionarioDiaria = funcionarioDiariaService.findByUnidade_idAndContaFuncionario_idAndDiaria_id(idFuncionario,idDiaria);
		return new ResponseEntity<FuncionarioDiaria>(funcionarioDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/{idFuncionario}/diaria/{idDiaria}")
	public ResponseEntity<FuncionarioDiaria> buscarFuncionarioPorId(@PathVariable Long idFuncionario, @PathVariable Long idDiaria){
		FuncionarioDiaria funcionarioDiaria = funcionarioDiariaService.findByUnidade_idAndContaFuncionarioFuncionario_idAndDiaria_id(idFuncionario,idDiaria);
		return new ResponseEntity<FuncionarioDiaria>(funcionarioDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/unidade/{idDiaria}")
	public ResponseEntity<List<FuncionarioDiariaDTO>> listaUnidade(@PathVariable Long idDiaria){
		List<FuncionarioDiariaDTO> list = funcionarioDiariaService.listaUnidade(idDiaria);
	//	List<FuncionarioDiariaDTO> listDto = list.stream().map(obj -> new FuncionarioDiariaDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<List<FuncionarioDiariaDTO>>(list, HttpStatus.OK);
	}
	
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<FuncionarioDiaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<FuncionarioDiaria>(funcionarioDiariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/buscar/diaria")
		public ResponseEntity<?> buscar(@RequestParam(defaultValue="0", required=false) int page
				,@RequestParam(defaultValue="20", required=false) int maxResults,
				@RequestParam("q")String texto) {
				return new ResponseEntity<Page<FuncionarioDiaria>>(funcionarioDiariaService.buscar(texto, new PageRequest(page, maxResults)), HttpStatus.OK);
		}
	 

}

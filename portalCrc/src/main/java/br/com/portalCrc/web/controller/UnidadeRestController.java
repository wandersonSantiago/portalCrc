package br.com.portalCrc.web.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.service.UnidadeService;

@RestController
@RequestMapping("/rest/unidade")
public class UnidadeRestController {

	@Autowired
	private UnidadeService unidadeService;

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<Unidade>> lista() {
		Iterable<Unidade> unidade = unidadeService.lista();
		return new ResponseEntity<Iterable<Unidade>>(unidade, HttpStatus.OK);
	}

	@GetMapping(value = "/coordenadoria/lista")
	public ResponseEntity<Iterable<Unidade>> listaUnidadeCoordenadoria() {
		Iterable<Unidade> unidade = unidadeService.listaUnidadeCoordenadoria();
		return new ResponseEntity<Iterable<Unidade>>(unidade, HttpStatus.OK);
	}

	@GetMapping(value = "/buscarUnidadePorCoordenadoria/{id}")
	public ResponseEntity<Iterable<Unidade>> buscaUnidadePorCoordenadoria(@PathVariable Long id) {
		Iterable<Unidade> unidade = unidadeService.buscaUnidadePorId(id);
		return new ResponseEntity<Iterable<Unidade>>(unidade, HttpStatus.OK);
	}

	@GetMapping(value = "/coordenadoria/{idCoordenadoria}/tipo-unidade/{idTipo}")
	public ResponseEntity<Iterable<Unidade>> findByCoordenadoriaAndTipo(@PathVariable Long idCoordenadoria,
			@PathVariable Long idTipo) {
		Iterable<Unidade> unidade = unidadeService.findByCoordenadoria_idAndTipo_id(idCoordenadoria, idTipo);
		return new ResponseEntity<Iterable<Unidade>>(unidade, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/tipo/{tipo}")
	public ResponseEntity<Iterable<Unidade>> buscarUnidadePorCoordenadoriaPorTipo(@PathVariable Long id,
			@PathVariable String tipo) {
		Iterable<Unidade> unidade = unidadeService.buscarUnidadePorCoordenadoriaPorTipo(id, tipo);
		return new ResponseEntity<Iterable<Unidade>>(unidade, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_?ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public ResponseEntity<Unidade> salvar(@RequestBody Unidade unidade, UriComponentsBuilder ucBuilder) {
		unidadeService.salvarEditar(unidade);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Unidade>(headers, HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('ROLE_?ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "/alterar")
	public ResponseEntity<Unidade> alterar(@RequestBody Unidade unidade, UriComponentsBuilder ucBuilder) {
		unidadeService.salvarEditar(unidade);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Unidade>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Unidade> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<Unidade>(unidadeService.buscaPorId(id), HttpStatus.OK);
	}

}

package br.com.portalCrc.web.controller.diaria;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.service.diaria.DiariaService;

@RestController
@RequestMapping("/rest/diaria")
public class DiariaRestController {

	@Autowired
	private DiariaService diariaService;
	
	@PostMapping
	 public ResponseEntity<Diaria> salvar(@RequestBody Diaria diaria,UriComponentsBuilder ucBuilder){
		diariaService.salvaOuAltera(diaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Diaria>(headers, HttpStatus.CREATED);
	 }
		
	@PutMapping
	public ResponseEntity<Diaria> alterar(@RequestBody Diaria diaria){
		diariaService.alterar(diaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@PutMapping(value="/encerrar")
	public ResponseEntity<Diaria> encerrar(@RequestBody Diaria diaria){
		diariaService.encerrar(diaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
/*	@GetMapping
	public ResponseEntity<Iterable<Diaria>> lista(){
		Iterable<Diaria> diaria	= diariaService.lista();
		return new ResponseEntity<Iterable<Diaria>>(diaria, HttpStatus.OK);
	}*/
	
	@GetMapping
	public ResponseEntity<Page<Diaria>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<Diaria> diaria = diariaService.lista(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Diaria>>(diaria, HttpStatus.OK);
	}

	
	@GetMapping(value="/diariasEmAberto")
	public ResponseEntity<Iterable<Diaria>> diariasEmAberto(){
		Iterable<Diaria> diaria	= diariaService.diariasEmAberto();
		return new ResponseEntity<Iterable<Diaria>>(diaria, HttpStatus.OK);
	}
	
	/*@GetMapping(value="/listaCoordenadoria")
	public ResponseEntity<Iterable<ItemDiaria>> listaCoordenadoria(){
		Iterable<ItemDiaria> diaria = diariaService.listaCoordenadoria();
		return new ResponseEntity<Iterable<ItemDiaria>>(diaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/listaUnidade")
	public ResponseEntity<Iterable<ItemDiaria>> listaUnidade(){
		Iterable<ItemDiaria> diaria = diariaService.listaUnidade();
		return new ResponseEntity<Iterable<ItemDiaria>>(diaria, HttpStatus.OK);
	}*/
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<Diaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Diaria>(diariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 @GetMapping(value = "/mes")
		public ResponseEntity<Iterable<MesDiariaEnum>> mesDiariaEnum() {
			Iterable<MesDiariaEnum> mesDiariaEnum = Arrays.asList(MesDiariaEnum.values());
			return new ResponseEntity<Iterable<MesDiariaEnum>>(mesDiariaEnum, HttpStatus.OK);
		}
	 
}

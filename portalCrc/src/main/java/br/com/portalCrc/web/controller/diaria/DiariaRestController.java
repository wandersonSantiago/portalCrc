package br.com.portalCrc.web.controller.diaria;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.diaria.Diaria;
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
		diariaService.salvaOuAltera(diaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Diaria>> lista(){
		Iterable<Diaria> diaria	= diariaService.lista();
		return new ResponseEntity<Iterable<Diaria>>(diaria, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<Diaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Diaria>(diariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 
}

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

import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.service.diaria.ItemDiariaService;


@RestController
@RequestMapping("/rest/diaria/item")
public class ItemDiariaRestController {

	@Autowired
	private ItemDiariaService itemdiariaService;
	
	@PostMapping
	 public ResponseEntity<ItemDiaria> salvar(@RequestBody ItemDiaria itemdiaria,UriComponentsBuilder ucBuilder){
		itemdiariaService.salvaOuAltera(itemdiaria);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ItemDiaria>(headers, HttpStatus.CREATED);
	 }
		
	@PutMapping
	public ResponseEntity<ItemDiaria> alterar(@RequestBody ItemDiaria itemdiaria){
		itemdiariaService.salvaOuAltera(itemdiaria);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ItemDiaria>> lista(){
		Iterable<ItemDiaria> diaria	= itemdiariaService.lista();
		return new ResponseEntity<Iterable<ItemDiaria>>(diaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/listaCoordenadoria/{id}")
	public ResponseEntity<Iterable<ItemDiaria>> listaCoordenadoria(@PathVariable Long id){
		Iterable<ItemDiaria> itemdiaria = itemdiariaService.listaCoordenadoria(id);
		return new ResponseEntity<Iterable<ItemDiaria>>(itemdiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/listaUnidade/{id}")
	public ResponseEntity<Iterable<ItemDiaria>> listaUnidade(@PathVariable Long id){
		Iterable<ItemDiaria> itemdiaria = itemdiariaService.listaUnidade(id);
		return new ResponseEntity<Iterable<ItemDiaria>>(itemdiaria, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<ItemDiaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ItemDiaria>(itemdiariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 

}

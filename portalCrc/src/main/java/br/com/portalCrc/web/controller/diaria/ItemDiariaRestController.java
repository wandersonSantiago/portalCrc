package br.com.portalCrc.web.controller.diaria;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.portalCrc.entity.diaria.ItemDashDTO;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;
import br.com.portalCrc.service.diaria.ItemDiariaService;


@RestController
@RequestMapping("/rest/diaria/item")
public class ItemDiariaRestController {

	@Autowired
	private ItemDiariaService itemDiariaService;
	
	@PreAuthorize("hasAnyRole('ROLE_?DIARIA_USUARIO', 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PostMapping
	 public ResponseEntity<ItemDiaria> salvar(@RequestBody ItemDiaria itemDiaria, UriComponentsBuilder ucBuilder){
		itemDiariaService.salvaOuAltera(itemDiaria);
		 return new ResponseEntity<ItemDiaria>(HttpStatus.CREATED);
	 }
		
	@PreAuthorize("hasAnyRole('ROLE_?DIARIA_USUARIO', 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<ItemDiaria> alterar(@RequestBody ItemDiaria itemDiaria, UriComponentsBuilder ucBuilder){
		itemDiariaService.altera(itemDiaria);
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?DIARIA_USUARIO', 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@DeleteMapping(value="/excluir/{id}")
	public ResponseEntity<ItemDiaria> excluir(@PathVariable Long id){
		itemDiariaService.excluir(id);
		return new ResponseEntity<>( HttpStatus.CREATED);		
	}
	
	@GetMapping(value="/coordenadoria")
	public ResponseEntity<Iterable<ItemDiaria>> listaCoordenadoria(@RequestParam(value="mes") MesDiariaEnum mes){
		Iterable<ItemDiaria> itemDiaria = itemDiariaService.listaCoordenadoria(mes);
		return new ResponseEntity<Iterable<ItemDiaria>>(itemDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/unidade/{idDiaria}")
	public ResponseEntity<Iterable<ItemDiaria>> listaUnidade(@PathVariable Long idDiaria){
		Iterable<ItemDiaria> itemDiaria = itemDiariaService.listaUnidade(idDiaria);
		return new ResponseEntity<Iterable<ItemDiaria>>(itemDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/itens/{idFuncionario}/tipo/{tipo}")
	public ResponseEntity<List<ItemDiaria>> itensAndTipo(@PathVariable Long idFuncionario, @PathVariable TipoDiariaEnum tipo){
		List<ItemDiaria> itemDiaria = itemDiariaService.findByFuncionarioDiaria_idAndTipo(idFuncionario, tipo);
		return new ResponseEntity<List<ItemDiaria>>(itemDiaria, HttpStatus.OK);
	}
	
	@GetMapping(value="/itens/{id}")
	public ResponseEntity<Iterable<ItemDiaria>> itens(@PathVariable Long id){
		Iterable<ItemDiaria> itemDiaria = itemDiariaService.findByFuncionarioDiaria_id(id);
		return new ResponseEntity<Iterable<ItemDiaria>>(itemDiaria, HttpStatus.OK);
	}
	
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<ItemDiaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ItemDiaria>(itemDiariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 
	 @PreAuthorize("hasAnyRole('ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	 @PostMapping(value="/{idItem}/analizado")
	 public ResponseEntity<ItemDiaria> analizado(@PathVariable Long idItem){
		 itemDiariaService.analizado(idItem);
		 return new ResponseEntity<ItemDiaria>(HttpStatus.CREATED);
	 }
	 
	 @PreAuthorize("hasAnyRole('ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	 @PostMapping(value="/{idItem}/retorno")
	 public ResponseEntity<ItemDiaria> retorno(@PathVariable Long idItem){
		 itemDiariaService.retorno(idItem);
		 return new ResponseEntity<ItemDiaria>(HttpStatus.CREATED);
	 }
	 
	 @GetMapping(value = "/tipos")
		public ResponseEntity<Iterable<TipoDiariaEnum>> tipoDiariaEnum() {
			Iterable<TipoDiariaEnum> tipoDiariaEnum = Arrays.asList(TipoDiariaEnum.values());
			return new ResponseEntity<Iterable<TipoDiariaEnum>>(tipoDiariaEnum, HttpStatus.OK);
		}

	 @GetMapping("/dash")
	 public ResponseEntity<List<ItemDashDTO>> getDashBoard(){
		 List<ItemDashDTO> list = itemDiariaService.getDashBoard();
		 return new ResponseEntity< List<ItemDashDTO>>(list, HttpStatus.OK);
	 }
}

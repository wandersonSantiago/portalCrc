package br.com.portalCrc.web.controller.controleIp;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.entity.controleIp.ServicosEquipamento;
import br.com.portalCrc.enums.controleIp.StatusEquipamento;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;
import br.com.portalCrc.service.controleIp.EquipamentoService;

@RestController
@RequestMapping("/rest/controleIp/equipamento")
public class EquipamentoRestController {
	
	
	@Autowired
	private EquipamentoService equipamentoService;
	
	@PreAuthorize("hasAnyRole('ROLE_?EQUIPAMENTO','ROLE_?ADMIN')")
	@PostMapping
	 public ResponseEntity<Equipamento> salvar(@RequestBody Equipamento equipamento,UriComponentsBuilder ucBuilder){
		equipamentoService.salvaOuAltera(equipamento);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Equipamento>(headers, HttpStatus.CREATED);
	 }
	
	@PreAuthorize("hasAnyRole('ROLE_?EQUIPAMENTO','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<Equipamento> alterar(@RequestBody Equipamento equipamento){
		equipamentoService.salvaOuAltera(equipamento);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?EQUIPAMENTO','ROLE_?ADMIN')")
	@PutMapping(value="/baixar")
	public ResponseEntity<Equipamento> baixar(@RequestBody Equipamento equipamento){
		equipamentoService.baixarEquipamento(equipamento);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	@GetMapping
	public ResponseEntity<Iterable<Equipamento>> lista(){
		Iterable<Equipamento> equipamento	= equipamentoService.lista();
		return new ResponseEntity<Iterable<Equipamento>>(equipamento, HttpStatus.OK);
	}
	@GetMapping(value="/ativo")
	public ResponseEntity<Iterable<Equipamento>> listarAtivos(){
		Iterable<Equipamento> equipamento	= equipamentoService.findByStatus(StatusEquipamento.ATIVO);
		return new ResponseEntity<Iterable<Equipamento>>(equipamento, HttpStatus.OK);
	}
	@GetMapping(value="/inativo")
	public ResponseEntity<Iterable<Equipamento>> listarInativos(){
		Iterable<Equipamento> equipamento	= equipamentoService.findByStatus(StatusEquipamento.INATIVO);
		return new ResponseEntity<Iterable<Equipamento>>(equipamento, HttpStatus.OK);
	}
	@GetMapping(value="/baixado")
	public ResponseEntity<Iterable<Equipamento>> listarBaixados(){
		Iterable<Equipamento> equipamento	= equipamentoService.findByStatus(StatusEquipamento.BAIXADO);
		return new ResponseEntity<Iterable<Equipamento>>(equipamento, HttpStatus.OK);
	}
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Equipamento>(equipamentoService.buscaPorId(id), HttpStatus.OK);
	 }
	 
	 
	 
	 //alterado aqui para carregar categoria equipamento
	 
	 @GetMapping(value = "/tipo/{tipo}")
		public ResponseEntity<List<Equipamento>> buscarPorTipo(@PathVariable TipoEquipamentoEnum tipo) {
			return new ResponseEntity<List<Equipamento>>(equipamentoService.buscarPorTipo(tipo), HttpStatus.OK);
	 }
	 
	 
	 
	 
	 
	 @GetMapping(value = "/{idEquipamento}/servicos")
		public ResponseEntity<List<ServicosEquipamento>> buscarServicos(@PathVariable Long idEquipamento) {
			return new ResponseEntity<List<ServicosEquipamento>>(equipamentoService.buscarServicos(idEquipamento), HttpStatus.OK);
	 }
	 
	 @GetMapping(value = "/tipoEquipamento")
		public ResponseEntity<Iterable<TipoEquipamentoEnum>> tipoEquipamento() {
			Iterable<TipoEquipamentoEnum> tipoEquipamentoEnum = Arrays.asList(TipoEquipamentoEnum.values());
			return new ResponseEntity<Iterable<TipoEquipamentoEnum>>(tipoEquipamentoEnum, HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/existe-patrimonio/{patrimonio}")
		public ResponseEntity<Equipamento> existePatrimonio(@PathVariable String patrimonio) {
			return new ResponseEntity<Equipamento>(equipamentoService.findByPatrimonio(patrimonio), HttpStatus.OK);
	 }

}

package br.com.portalCrc.web.controller.controleIp;

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

import br.com.portalCrc.entity.controleIp.ManutencaoEquipamento;
import br.com.portalCrc.service.controleIp.ManutencaoEquipamentoService;

@RestController
@RequestMapping("/rest/controleIp/equipamento/manutencao")
public class ManutencaoEquipamentoRestController {

	
	@Autowired
	private ManutencaoEquipamentoService manutencaoEquipamentoService; 
	
	@PreAuthorize("hasAnyRole('ROLE_?EQUIPAMENTO','ROLE_?ADMIN')")
	@PostMapping
	public ResponseEntity<ManutencaoEquipamento> salvarOuAlterar(@RequestBody ManutencaoEquipamento manutencaoEquipamento){		
		manutencaoEquipamentoService.salvar(manutencaoEquipamento);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http, HttpStatus.CREATED);	
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?EQUIPAMENTO','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<ManutencaoEquipamento> alterar(@RequestBody ManutencaoEquipamento manutencaoEquipamento){
		manutencaoEquipamentoService.alterar(manutencaoEquipamento);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ManutencaoEquipamento>> lista(){
		Iterable<ManutencaoEquipamento> manutencaoEquipamento	= manutencaoEquipamentoService.lista();
		return new ResponseEntity<Iterable<ManutencaoEquipamento>>(manutencaoEquipamento, HttpStatus.OK);
	}
	
	@GetMapping(value="/realizado")
	public ResponseEntity<Iterable<ManutencaoEquipamento>> listaAtivos(){
		Boolean status = true;
		Iterable<ManutencaoEquipamento> manutencaoEquipamento	= manutencaoEquipamentoService.findByStatusNotNull(status);
		return new ResponseEntity<Iterable<ManutencaoEquipamento>>(manutencaoEquipamento, HttpStatus.OK);
	}
	
	@GetMapping(value="/agendado")
	public ResponseEntity<Iterable<ManutencaoEquipamento>> fyndByStatusAndTecnicoIsNull(){
		Boolean status = true;
		Iterable<ManutencaoEquipamento> manutencaoEquipamento	= manutencaoEquipamentoService.findByStatusIsNull(status);
		return new ResponseEntity<Iterable<ManutencaoEquipamento>>(manutencaoEquipamento, HttpStatus.OK);
	}
	
	@GetMapping(value="/ativos/prioridade")
	public ResponseEntity<Iterable<ManutencaoEquipamento>> listaAtivosPrioridade(){
		Boolean status = true;
		Iterable<ManutencaoEquipamento> manutencaoEquipamento = manutencaoEquipamentoService
				.buscarPreventivasPrioridade(status);
		return new ResponseEntity<Iterable<ManutencaoEquipamento>>(manutencaoEquipamento, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<ManutencaoEquipamento> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ManutencaoEquipamento>(manutencaoEquipamentoService.buscaPorId(id), HttpStatus.OK);
	 }
	 
}

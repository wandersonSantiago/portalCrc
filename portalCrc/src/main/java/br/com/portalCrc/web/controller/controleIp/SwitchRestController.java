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

import br.com.portalCrc.entity.controleIp.Switch;
import br.com.portalCrc.enums.controleIp.StatusSwitch;
import br.com.portalCrc.service.controleIp.SwitchService;

@RestController
@RequestMapping("/rest/controleIp/switch")
public class SwitchRestController {
		
	@Autowired
	private SwitchService switchService;
	
	@PreAuthorize("hasAnyRole('ROLE_?SWITCH','ROLE_?ADMIN')")
	@PostMapping
	public ResponseEntity<Switch>salvar(@RequestBody Switch switchs){
		switchService.salvaOuAltera(switchs);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?SWITCH','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<Switch> alterar(@RequestBody Switch switchs){
		switchService.salvaOuAltera(switchs);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Switch>> lista(){
		Iterable<Switch> switchs = switchService.lista();
		return new ResponseEntity<Iterable<Switch>>(switchs, HttpStatus.OK);
		
	}
	@GetMapping(value="/ativos")
	public ResponseEntity<Iterable<Switch>> listaAtivos(){
		Iterable<Switch> switchs = switchService.listaPorStatus(StatusSwitch.ATIVO);
		return new ResponseEntity<Iterable<Switch>>(switchs, HttpStatus.OK);
		
	}
	@GetMapping(value="/inativos")
	public ResponseEntity<Iterable<Switch>> listaInativos(){
		Iterable<Switch> switchs = switchService.listaPorStatus(StatusSwitch.INATIVO);
		return new ResponseEntity<Iterable<Switch>>(switchs, HttpStatus.OK);
		
	}
	@GetMapping(value="/baixados")
	public ResponseEntity<Iterable<Switch>> listaBaixados(){
		Iterable<Switch> switchs = switchService.listaPorStatus(StatusSwitch.BAIXADO);
		return new ResponseEntity<Iterable<Switch>>(switchs, HttpStatus.OK);
		
	}
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Switch> buscaPorId(@PathVariable long id) {
		return new ResponseEntity<Switch>(switchService.buscaPorId(id), HttpStatus.OK );
		
	}
}
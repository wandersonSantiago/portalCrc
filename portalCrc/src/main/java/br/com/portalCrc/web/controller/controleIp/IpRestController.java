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

import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.enums.controleIp.StatusIp;
import br.com.portalCrc.service.controleIp.IpService;

@RestController
@RequestMapping("/rest/controleIp/ip")
public class IpRestController {
		
	@Autowired
	private IpService ipService;
	
	@PreAuthorize("hasAnyRole('ROLE_?IP','ROLE_?ADMIN')")
	@PostMapping
	public ResponseEntity<Ip> salvar(@RequestBody Ip ip){
		ipService.salvar(ip);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_?IP','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<Ip> Alterar(@RequestBody Ip ip){
		ipService.altera(ip);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Ip>> lista(){
		Iterable<Ip> ip	= ipService.lista();
		return new ResponseEntity<Iterable<Ip>>(ip, HttpStatus.OK);
	}
	
	@GetMapping(value ="/inativo")
	public ResponseEntity<Iterable<Ip>> listaIpInativo(){
		Iterable<Ip> ip	= ipService.listaIp(StatusIp.INATIVO);
		return new ResponseEntity<Iterable<Ip>>(ip, HttpStatus.OK);
	}
	@GetMapping(value ="/ativo")
	public ResponseEntity<Iterable<Ip>> listaIpAtivo(){
		Iterable<Ip> ip	= ipService.listaIp(StatusIp.ATIVO);
		return new ResponseEntity<Iterable<Ip>>(ip, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<Ip> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Ip>(ipService.buscaPorId(id), HttpStatus.OK);
		}

}
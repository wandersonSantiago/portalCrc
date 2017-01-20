package br.com.portalCrc.web.controller.controleIp;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.service.controleIp.IpService;

@RestController
@RequestMapping("/rest/controleIp/ip")
public class IpRestController {
		
	@Autowired
	private IpService ipService;
	
	@PostMapping
	public ResponseEntity<Ip> salvarOuAlterar(@RequestBody Ip ip)
	{
		ipService.salvaOuAltera(ip);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);
		
	}
	
	@PutMapping
	public ResponseEntity<Ip> Alterar(@RequestBody Ip ip){
		ipService.salvaOuAltera(ip);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Ip>> lista(){
		Iterable<Ip> ip	= ipService.lista();
		return new ResponseEntity<Iterable<Ip>>(ip, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<Ip> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Ip>(ipService.buscaPorId(id), HttpStatus.OK);
		}

}
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
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.controleIp.TipoIp;
import br.com.portalCrc.service.controleIp.TipoIpService;

@RestController
@RequestMapping("/rest/controleIp/tipoIp")
public class TipoIpRestController {
	
	@Autowired
	private TipoIpService tipoIpService;
	
	@PostMapping
	public ResponseEntity<TipoIp> salvarOuAlterar(@RequestBody TipoIp tipoIp)
	{
		tipoIpService.salvaOuAltera(tipoIp);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);
				
	}
	
	@PutMapping
	public ResponseEntity<TipoIp> alterar(@RequestBody TipoIp tipoIp){
		tipoIpService.salvaOuAltera(tipoIp);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<TipoIp>> lista(){
		Iterable<TipoIp> tipoIp	= tipoIpService.lista();
		return new ResponseEntity<Iterable<TipoIp>>(tipoIp, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<TipoIp> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<TipoIp>(tipoIpService.buscaPorId(id), HttpStatus.OK);
	 }

}

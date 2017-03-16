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

import br.com.portalCrc.entity.controleIp.PortaSwitch;
import br.com.portalCrc.service.controleIp.PortaSwitchService;

@RestController
@RequestMapping("/rest/controleIp/switch/porta")
public class PortaSwitchController {


	@Autowired
	private PortaSwitchService switchPortaService; 
	
	@PostMapping
	public ResponseEntity<PortaSwitch> salvarOuAlterar(@RequestBody PortaSwitch porta){		
		switchPortaService.salvaOuAltera(porta);
		HttpHeaders http = new HttpHeaders();
		return new ResponseEntity<>(http, HttpStatus.CREATED);
	
	}
	
	@PutMapping
	public ResponseEntity<PortaSwitch> alterar(@RequestBody PortaSwitch porta){
		switchPortaService.salvaOuAltera(porta);
		HttpHeaders http =  new HttpHeaders();
		return new ResponseEntity<>(http , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<Iterable<PortaSwitch>> lista(){
		Iterable<PortaSwitch> porta	= switchPortaService.lista();
		return new ResponseEntity<Iterable<PortaSwitch>>(porta, HttpStatus.OK);
	}
	
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<PortaSwitch> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<PortaSwitch>(switchPortaService.buscaPorId(id), HttpStatus.OK);
	 }
}

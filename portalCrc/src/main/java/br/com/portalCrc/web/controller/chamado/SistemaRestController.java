package br.com.portalCrc.web.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.Sistema;
import br.com.portalCrc.service.chamado.SistemaService;

@RestController
@RequestMapping("/rest/chamado/sistema")
public class SistemaRestController {

	@Autowired
	private SistemaService sistemaService;
	
	 @PostMapping
	 public ResponseEntity<Sistema> salvar(@RequestBody Sistema sistema,UriComponentsBuilder ucBuilder){
		 sistemaService.salvarOuEditar(sistema);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Sistema>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping
	 public ResponseEntity<Iterable<Sistema>> listaSuporte() {	  
	  Iterable<Sistema> sistema = sistemaService.findAll();
	  return new ResponseEntity<Iterable<Sistema>>(sistema, HttpStatus.OK);
	 }
}

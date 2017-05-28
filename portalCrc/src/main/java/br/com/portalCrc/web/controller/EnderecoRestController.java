package br.com.portalCrc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.Cidade;
import br.com.portalCrc.entity.Estado;
import br.com.portalCrc.service.CidadeService;
import br.com.portalCrc.service.EstadoService;

@RestController
@RequestMapping("/rest/endereco")
public class EnderecoRestController {

	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EstadoService estadoService;
	
	 @GetMapping(value="/cidade/estado/{idEstado}")
	 public ResponseEntity<Iterable<Cidade>> cidades(@PathVariable Long idEstado) {	  
	  Iterable<Cidade> cidade = cidadeService.lista(idEstado);
	  return new ResponseEntity<Iterable<Cidade>>(cidade, HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/estado/pais/{idPais}")
	 public ResponseEntity<Iterable<Estado>> estados(@PathVariable Long idPais) {	  
	  Iterable<Estado> estado = estadoService.lista(idPais);
	  return new ResponseEntity<Iterable<Estado>>(estado, HttpStatus.OK);
	 }
}

package br.com.portalCrc.web.controller.infra;

import java.util.List;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.infra.Cor;
import br.com.portalCrc.entity.infra.Marca;
import br.com.portalCrc.entity.infra.Modelo;
import br.com.portalCrc.entity.infra.Veiculo;
import br.com.portalCrc.service.infra.VeiculoService;

@RestController
@RequestMapping("/rest/infra/veiculo")
public class VeiculoRestController {

	@Autowired
	private VeiculoService veiculoService;
	
	 @GetMapping
	 public ResponseEntity<Iterable<Veiculo>> lista() {	  
	  Iterable<Veiculo> veiculo = veiculoService.lista();
	  return new ResponseEntity<Iterable<Veiculo>>(veiculo, HttpStatus.OK);
	 }
	 
	 
	 @PostMapping
	 public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo veiculo,UriComponentsBuilder ucBuilder){
		 veiculoService.salvarEditar(veiculo);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Veiculo>(headers, HttpStatus.CREATED);
	 }

	 @PutMapping
	 public ResponseEntity<Veiculo> alterar(@RequestBody Veiculo veiculo,UriComponentsBuilder ucBuilder){
		 veiculoService.salvarEditar(veiculo);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Veiculo>(headers, HttpStatus.CREATED);
	 }

	 
	 @GetMapping(value = "/buscaPorId/{id}")
		public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Veiculo>(veiculoService.buscaPorId(id), HttpStatus.OK);
		}
	 
	 @GetMapping(value="/marcas")
	 public ResponseEntity<List<Marca>> marcas() {	  
	  List<Marca> marca = veiculoService.findAllMarcas();
	  return new ResponseEntity<List<Marca>>(marca, HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/cor")
	 public ResponseEntity<List<Cor>> cores() {	  
	  List<Cor> cor = veiculoService.findAllCores();
	  return new ResponseEntity<List<Cor>>(cor, HttpStatus.OK);
	 }
	 
	 @GetMapping(value="/modelos/marca/{idMarca}")
	 public ResponseEntity<List<Modelo>> modelos(@PathVariable Long idMarca) {	  
	  List<Modelo> modelo = veiculoService.findAllModelo(idMarca);
	  return new ResponseEntity<List<Modelo>>(modelo, HttpStatus.OK);
	 }
	 @GetMapping(value="/unidade/{idUnidade}")
	 public ResponseEntity<List<Veiculo>> veiculos(@PathVariable Long idUnidade) {	  
	  List<Veiculo> veiculo = veiculoService.findByUnidade_id(idUnidade);
	  return new ResponseEntity<List<Veiculo>>(veiculo, HttpStatus.OK);
	 }
	 
}

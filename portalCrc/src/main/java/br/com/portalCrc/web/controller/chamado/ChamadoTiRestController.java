package br.com.portalCrc.web.controller.chamado;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.enums.chamado.PrioridadeChamado;
import br.com.portalCrc.service.chamado.ChamadoTiService;

@RestController
@RequestMapping("/rest/chamado/chamadoTi")
public class ChamadoTiRestController {

	
	@Autowired
	private ChamadoTiService chamadoTiService;
	
	
	 @RequestMapping(method = RequestMethod.GET, value="/suporte/lista")
	 public ResponseEntity<Iterable<ChamadoTi>> listaSuporte() {	  
	  Iterable<ChamadoTi> chamadoTi = chamadoTiService.listaSuporte();
	  return new ResponseEntity<Iterable<ChamadoTi>>(chamadoTi, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/usuario/lista")
	 public ResponseEntity<Iterable<ChamadoTi>> listaUsuario() {	  
	  Iterable<ChamadoTi> chamadoTi = chamadoTiService.listaChamadoTiUsuario();
	  return new ResponseEntity<Iterable<ChamadoTi>>(chamadoTi, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<ChamadoTi> salvar(@RequestBody ChamadoTi chamadoTi,UriComponentsBuilder ucBuilder){
		 chamadoTiService.salvarEditar(chamadoTi);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	 }

	 @RequestMapping(method = RequestMethod.PUT, value="/alterar")
	 public ResponseEntity<ChamadoTi> alterar(@RequestBody ChamadoTi chamadoTi,UriComponentsBuilder ucBuilder){
		 chamadoTiService.salvarEditar(chamadoTi);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	 }

	
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<ChamadoTi> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ChamadoTi>(chamadoTiService.buscaPorId(id), HttpStatus.OK);
		}
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/prioridade")
		public ResponseEntity<Iterable<PrioridadeChamado>> prioridade() {
			Iterable<PrioridadeChamado> prioridadeChamado = Arrays.asList(PrioridadeChamado.values());
			return new ResponseEntity<Iterable<PrioridadeChamado>>(prioridadeChamado, HttpStatus.OK);
		}
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/mensagem")
	 public ResponseEntity<ChamadoTi> mensagem(@RequestBody ChamadoTi chamadoTi,UriComponentsBuilder ucBuilder){
		 chamadoTiService.mensagens(chamadoTi);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/atender")
	 public ResponseEntity<ChamadoTi> atender(@RequestBody ChamadoTi chamadoTi,UriComponentsBuilder ucBuilder){
		 chamadoTiService.atenderChamado(chamadoTi);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/fechar")
	 public ResponseEntity<ChamadoTi> fechar(@RequestBody ChamadoTi chamadoTi,UriComponentsBuilder ucBuilder){
		 chamadoTiService.fecharChamado(chamadoTi);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<ChamadoTi>(headers, HttpStatus.CREATED);
	 }
	
}

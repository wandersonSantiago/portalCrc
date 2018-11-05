package br.com.portalCrc.web.controller.auditorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.auditorio.Reserva;
import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.service.auditorio.ReservaService;

@RestController
@RequestMapping("/rest/agendamento/reserva")

public class ReservaRestController {
	
	@Autowired
	private ReservaService reservaService;
	
	@GetMapping
	public ResponseEntity<Iterable<Reserva>> lista(){
		   Iterable<Reserva> reserva = reservaService.lista();
		   return new ResponseEntity<Iterable<Reserva>>(reserva, HttpStatus.OK);
		}
	
	@PostMapping
	public ResponseEntity<Reserva> salvar (@RequestBody Reserva reserva, UriComponentsBuilder ucBuilder) {
		reservaService.salvarEditar(reserva);
		HttpHeaders headers =new HttpHeaders();
		return new ResponseEntity<Reserva>(headers, HttpStatus.CREATED);
					
		
	}  
	
	@PutMapping
	 public ResponseEntity<Reserva> alterar(@RequestBody Reserva reserva,UriComponentsBuilder ucBuilder){
		 reservaService.salvarEditar(reserva);
		 HttpHeaders headers =new HttpHeaders();
	 return new ResponseEntity<Reserva>(headers, HttpStatus.CREATED);
	 }
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) {
		Reserva reserva = reservaService.buscaPorId(id);
		System.out.println(reserva);
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
		}
	
	
	 @GetMapping(value = "/descricao")
		public ResponseEntity<Page<Reserva>> findByDescricao(
				@RequestParam(value="page", defaultValue="0") Integer page, 
				@RequestParam(value="linesPerPage", defaultValue="2") Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="funcionario") String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC") String direction,
				@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

			Page<Reserva> list = null;
			
			if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
				list = reservaService.findAll(new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy));
			}else {
				list = reservaService.buscar(descricao, new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy));
			}
			
			return ResponseEntity.ok().body(list);
		}
	
	
	

}

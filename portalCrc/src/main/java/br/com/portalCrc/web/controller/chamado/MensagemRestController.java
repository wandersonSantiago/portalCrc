package br.com.portalCrc.web.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.chamado.Mensagem;
import br.com.portalCrc.service.chamado.MensagemService;

@RestController
@RequestMapping("/rest/chamado/mensagem")
public class MensagemRestController {
		
	@Autowired
	private MensagemService mensagemService;
	
	 @RequestMapping(method = RequestMethod.POST, value="/salvar")
	 public ResponseEntity<Mensagem> salvar(@RequestBody Mensagem mensagem,UriComponentsBuilder ucBuilder){
		 System.out.println(mensagem.getTexto());	
		 
		
		 mensagemService.salvarEditar(mensagem);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Mensagem>(headers, HttpStatus.CREATED);
	 }
}

package br.com.portalCrc.web.controller.chamado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.chamado.Mensagem;
import br.com.portalCrc.entity.chamado.MensagemDTO;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.service.chamado.MensagemService;

@RestController
@RequestMapping("/rest/chamado/mensagem")
public class MensagemRestController {
	
	@Autowired
	private MensagemService mensagemService;
	

	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PostMapping
	 public MensagemDTO insert(@RequestBody MensagemDTO mensagemDTO){
		 mensagemDTO.setUsuario(SessionUsuario.getInstance().getUsuario());  
		Mensagem mensagem =  mensagemService.convertObject(mensagemDTO);
		 return mensagemService.insert(mensagem);
	 }
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PostMapping("/lidas")
	 public void marcarComoLido(@RequestBody List<Mensagem> mensagens){
		  mensagemService.marcarComoLido(mensagens);
	 }
		 
}

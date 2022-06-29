package br.com.portalCrc.web.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.repository.UsuarioRepository;
import br.com.portalCrc.service.chamado.ChamadoTiService;
import br.com.portalCrc.util.Result;


@Controller
public class WebSocketController {
		
	@Autowired
	private ChamadoTiService chamadoTiService;
	@Autowired
	private UsuarioRepository userRepository;
    
	@MessageMapping("/add" )
    @SendTo("/topic/showResult")
    public Result addNum(Result chatMessage) throws Exception {	
		Usuario user = userRepository.findByLogin(chatMessage.getUser());
	return	chamadoTiService.enviarMensagemDeAvisoDeChamadoAberto(chatMessage, user);
    }
   
}

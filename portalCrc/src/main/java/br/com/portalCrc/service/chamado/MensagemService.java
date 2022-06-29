package br.com.portalCrc.service.chamado;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.chamado.Chamado;
import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.entity.chamado.Mensagem;
import br.com.portalCrc.entity.chamado.MensagemDTO;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.MensagemRepository;
import br.com.portalCrc.util.Result;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class MensagemService {

	@Autowired
	private MensagemRepository mensagemRepository;
	
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
    @Autowired
	private MessageSendingOperations<String> messagingTemplate;
    
    
    @Transactional(readOnly= false)
    public MensagemDTO insert(Mensagem mensagem) {
    	mensagem.setData(new Date());
    	return new MensagemDTO(mensagemRepository.save(mensagem));
    }

    public Mensagem convertObject(MensagemDTO mensagemDTO) {
		return new Mensagem(mensagemDTO.getId(), mensagemDTO.getChamado(), mensagemDTO.getTexto(),mensagemDTO.getData(), mensagemDTO.getUsuario());
	}
    
    
    
    
	public Result enviarMensagemDeAvisoDeChamadoAberto() {		
		Integer count =  0;	
		Result result = new Result("Novo Chamado", count);
		return result;
	}
	
	//@Scheduled(cron = "1 * * * * *", zone= TIME_ZONE)
	public void schedule() {		
		if(1 > 0) {
			this.messagingTemplate.convertAndSend("/topic/showResult", new Result("Chamado não atendido", 0));
		}		 	
		
	}

	@Transactional(readOnly= false)
	public void marcarComoLido(List<Mensagem> mensagens, Long idChamado) {
		
		mensagens.forEach(mensagem ->{
			if(mensagem.getUsuario().equals(SessionUsuario.getInstance().getUsuario())) {
				Chamado chamado = new ChamadoTi();
				chamado.setId(idChamado);
				mensagem.setChamado(chamado);
				mensagem.setLido(true);
				mensagemRepository.save(mensagem);
			}
		});
		
	}

	

	

}

package br.com.portalCrc.service.chamado;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.Mensagem;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.MensagemRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class MensagemService {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Mensagem mensagem){
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		//mensagem.setData(LocalDateTime.now());
		mensagem.setUsuario(usuario);
		mensagemRepository.save(mensagem);
	}

}

package br.com.portalCrc.service.chamado;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoManutencao;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.ChamadoManutencaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoManutencaoService {

	
	@Autowired
	private ChamadoManutencaoRepository chamadoManutencaoRepository;
	
		
	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoManutencao chamadoManutencao){
		Date dataAtual = new Date();
		chamadoManutencao.setSetor(SessionUsuario.getInstance().getUsuario().getSetor());
		chamadoManutencao.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		chamadoManutencao.setUsuarioSolicitante(SessionUsuario.getInstance().getUsuario());
		chamadoManutencao.setStatus(StatusChamado.Aberto);
		chamadoManutencao.setLido(false);
		chamadoManutencao.setSilenciar(false);
		chamadoManutencao.setDataAbertura(dataAtual);
		adicionarChamadoNasMensagens(chamadoManutencao);
	
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	@Transactional(readOnly = false)
	public void servicos(ChamadoManutencao chamadoManutencao){
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	@Transactional(readOnly = false)
	public void mensagens(ChamadoManutencao chamadoManutencao){
		adicionarChamadoNasMensagens(chamadoManutencao);	
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	@Transactional(readOnly = false)
	public void atenderChamado(ChamadoManutencao chamadoManutencao){
		chamadoManutencao.setStatus(StatusChamado.Em_Andamento);
		chamadoManutencao.setLido(true);
		chamadoManutencao.setUsuarioAtendente(SessionUsuario.getInstance().getUsuario());
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	@Transactional(readOnly = false)
	public void fecharChamado(ChamadoManutencao chamadoManutencao){
		Date dataAtual = new Date();
		chamadoManutencao.setStatus(StatusChamado.Fechado);
		chamadoManutencao.setDataFechamento(dataAtual);	
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	public Collection<ChamadoManutencao> listaChamadoManutencaoUsuario(){
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		return chamadoManutencaoRepository.listaChamadoUsuario(usuario);
	}
	public Collection<ChamadoManutencao> listaSuporte(){
		return chamadoManutencaoRepository.findAll();
	}
	
	public ChamadoManutencao buscaPorId(Long id){
		return chamadoManutencaoRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoTrue(ChamadoManutencao chamado){
		chamado.setSilenciar(true);
		chamadoManutencaoRepository.save(chamado);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoFalse(ChamadoManutencao chamado){
		chamado.setSilenciar(false);
		chamadoManutencaoRepository.save(chamado);
	}
	
	public ChamadoManutencao adicionarChamadoNasMensagens(ChamadoManutencao chamadoManutencao){
		Date dataAtual = new Date();
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		for(int i = 0 ; i < chamadoManutencao.getMensagens().size() ; i ++ ){
			chamadoManutencao.getMensagens().get(i).setChamado(chamadoManutencao);
			chamadoManutencao.getMensagens().get(i).setData(dataAtual);
			chamadoManutencao.getMensagens().get(i).setUsuario(usuario);
		}
		return chamadoManutencao;
	}

}

package br.com.portalCrc.service.chamado;

import java.time.LocalDateTime;
import java.util.Collection;

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
		chamadoManutencao.setSetor(SessionUsuario.getInstance().getUsuario().getSetor());
		chamadoManutencao.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		chamadoManutencao.setUsuarioSolicitante(SessionUsuario.getInstance().getUsuario());
		chamadoManutencao.setStatus(StatusChamado.Aberto);
		chamadoManutencao.setDataAbertura(LocalDateTime.now());
		adicionarChamadoNasMensagens(chamadoManutencao);
	
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
	
	
	public void adicionarChamadoNasMensagens(ChamadoManutencao chamadoManutencao){
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		for(int i = 0 ; i < chamadoManutencao.getMensagens().size() ; i ++ ){
			chamadoManutencao.getMensagens().get(i).setChamado(chamadoManutencao);
			chamadoManutencao.getMensagens().get(i).setData(LocalDateTime.now());;
			chamadoManutencao.getMensagens().get(i).setUsuario(usuario);
		}
	}

}

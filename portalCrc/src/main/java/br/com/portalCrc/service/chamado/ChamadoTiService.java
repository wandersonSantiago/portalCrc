package br.com.portalCrc.service.chamado;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.ChamadoTiRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoTiService {

	
	@Autowired
	private ChamadoTiRepository chamadoTiRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoTi chamadoTi){
		chamadoTi.setSetor(SessionUsuario.getInstance().getUsuario().getSetor());
		chamadoTi.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		chamadoTi.setUsuarioSolicitante(SessionUsuario.getInstance().getUsuario());
		chamadoTi.setStatus(StatusChamado.Aberto);
		chamadoTi.setDataAbertura(LocalDateTime.now());
		adicionarChamadoNasMensagens(chamadoTi);
		
		chamadoTiRepository.save(chamadoTi);
	}
	
	public Collection<ChamadoTi> listaChamadoTiUsuario(){
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		return chamadoTiRepository.listaChamadoUsuario(usuario);
	}
	
	public Collection<ChamadoTi> listaSuporte(){
		return chamadoTiRepository.findAll();
	}
	
	public ChamadoTi buscaPorId(Long id){
		return chamadoTiRepository.findOne(id);
	}
	
	public void adicionarChamadoNasMensagens(ChamadoTi chamadoTi){
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		for(int i = 0; i < chamadoTi.getMensagens().size() ; i ++){
			chamadoTi.getMensagens().get(i).setChamado(chamadoTi);
			chamadoTi.getMensagens().get(i).setData(LocalDateTime.now());;
			chamadoTi.getMensagens().get(i).setUsuario(usuario);
		}
	}
}

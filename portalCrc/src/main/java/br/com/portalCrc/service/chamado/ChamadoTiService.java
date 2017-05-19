package br.com.portalCrc.service.chamado;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.pojo.ConverteData;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.ChamadoTiRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoTiService {

	
	@Autowired
	private ChamadoTiRepository chamadoTiRepository;
	
	private Date dataAtual;
	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoTi chamadoTi){
		dataAtual = new Date();
		chamadoTi.setSetor(SessionUsuario.getInstance().getUsuario().getFuncionario().getSetorAtual());
		chamadoTi.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		chamadoTi.setUsuarioSolicitante(SessionUsuario.getInstance().getUsuario());
		chamadoTi.setStatus(StatusChamado.ABERTO);
		chamadoTi.setLido(false);
		chamadoTi.setSilenciar(false);
		chamadoTi.setDataAbertura(dataAtual);
		adicionarChamadoNasMensagens(chamadoTi);
		
		chamadoTiRepository.save(chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void servicos(ChamadoTi chamadoTi){
		chamadoTiRepository.save( chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void mensagens(ChamadoTi chamadoTi){
		adicionarChamadoNasMensagens(chamadoTi);	
		chamadoTiRepository.save(chamadoTi);
	}
	@Transactional(readOnly = false)
	public void atenderChamado(ChamadoTi chamadoTi){	
		chamadoTi.setStatus(StatusChamado.EM_ANDAMENTO);
		chamadoTi.setLido(true);
		chamadoTi.setUsuarioAtendente(SessionUsuario.getInstance().getUsuario());
		chamadoTiRepository.save(chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void fecharChamado(ChamadoTi chamadoTi){
		dataAtual = new Date();	
		chamadoTi.setStatus(StatusChamado.FECHADO);
		chamadoTi.setDataFechamento(dataAtual);	
		chamadoTiRepository.save(chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoTrue(ChamadoTi chamado){
		chamado.setSilenciar(true);
		chamadoTiRepository.save(chamado);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoFalse(ChamadoTi chamado){
		chamado.setSilenciar(false);
		chamadoTiRepository.save(chamado);
	}
	
	
	public Collection<ChamadoTi> listaChamadoTiUsuario(){
		Usuario usuario = new Usuario();
		Unidade unidade =  new Unidade();
		usuario = SessionUsuario.getInstance().getUsuario();
		unidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual();
		return chamadoTiRepository.listaChamadoUsuario(usuario, unidade);
	}
	
	public Collection<ChamadoTi> listaSuporte(){
		Unidade unidade =  new Unidade();
		unidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual();
		return chamadoTiRepository.listaSuporte(unidade);
	}
	
	public ChamadoTi buscaPorId(Long id){
		return chamadoTiRepository.findOne(id);
	}
	
	public void adicionarChamadoNasMensagens(ChamadoTi chamadoTi){
		dataAtual = new Date();
		Usuario usuario = new Usuario();
		usuario = SessionUsuario.getInstance().getUsuario();
		for(int i = 0; i < chamadoTi.getMensagens().size() ; i ++){
			chamadoTi.getMensagens().get(i).setChamado(chamadoTi);
			chamadoTi.getMensagens().get(i).setData(dataAtual);
			chamadoTi.getMensagens().get(i).setUsuario(usuario);
		}
	}

	public Page<ChamadoTi> relatorio(PageRequest page) {
		return chamadoTiRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), page);
	}
	
	public Collection<ChamadoTi> relatorio(Date dataInicial, Date dataFinal) {
		
		return chamadoTiRepository.relatorio(new ConverteData(dataInicial).getString(),new ConverteData(dataFinal).getString(), SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

	public Page<ChamadoTi> buscarPorUnidadeComPaginacao(PageRequest pageRequest) {		
		return chamadoTiRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId() , pageRequest);
	}
	
	public Iterable<ChamadoTi> relatorioPorDataETitulo(Date dataInicial, Date dataFinal, String titulo) {
		return chamadoTiRepository.relatorioPorDataETitulo(new ConverteData(dataInicial).getString(),new ConverteData(dataFinal).getString(), titulo, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
}

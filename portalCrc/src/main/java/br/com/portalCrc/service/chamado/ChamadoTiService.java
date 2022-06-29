package br.com.portalCrc.service.chamado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.entity.chamado.QChamadoTi;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.pojo.ConverteData;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.chamado.ChamadoTiRepository;
import br.com.portalCrc.service.diaria.MensagemException;
import br.com.portalCrc.util.Result;
import br.com.portalCrc.web.controller.chamado.filters.ChamadoFilter;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoTiService {

	@Autowired
	private ChamadoTiRepository chamadoTiRepository;
	
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
    @Autowired
	private MessageSendingOperations<String> messagingTemplate;
    

	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoTi chamadoTi) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		chamadoTi.setSetor(user.getFuncionario().getSetorAtual());
		chamadoTi.setUnidade(user.getFuncionario().getUnidadeAtual());
		chamadoTi.setUsuarioSolicitante(user);
		chamadoTi.setStatus(StatusChamado.ABERTO);
		chamadoTi.setLido(false);
		chamadoTi.setSilenciar(false);
		chamadoTi.setDataAbertura(new Date());
		chamadoTiRepository.save(chamadoTi);
		
		
	}

	@Transactional(readOnly = false)
	public void servicos(ChamadoTi chamadoTi) {
		buscaPorId(chamadoTi.getId());
		chamadoTi.getServicos().setDataCadastro(new Date());
		chamadoTi.getServicos().setTecnico(SessionUsuario.getInstance().getUsuario());
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void mensagens(ChamadoTi chamadoTi) {
		adicionarChamadoNasMensagens(chamadoTi);
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void atenderChamado(ChamadoTi chamadoTi) {		
		buscaPorId(chamadoTi.getId());	
		chamadoTi.setStatus(StatusChamado.EM_ANDAMENTO);
		chamadoTi.setLido(true);
		chamadoTi.setUsuarioAtendente(SessionUsuario.getInstance().getUsuario());
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void fecharChamado(ChamadoTi chamadoTi) {
		buscaPorId(chamadoTi.getId());
		chamadoTi.setStatus(StatusChamado.FECHADO);
		chamadoTi.setDataFechamento(new Date());
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void silenciarChamado(ChamadoTi chamado) {
		boolean b = chamado.getSilenciar() == true ? false : true;
		chamado.setSilenciar(b);
		chamadoTiRepository.save(chamado);
	}
	

	public ChamadoTi buscaPorId(Long id) {		
		ChamadoTi chamado =	chamadoTiRepository.findOne(id);
		Usuario user = SessionUsuario.getInstance().getUsuario();			
		if(user.hasRole("CHAMADO_USUARIO")) {
			if(!chamado.getUsuarioSolicitante().equals(user)) {
				throw new MensagemException("Usuario não tem permissão de acessar este chamado!!!");
			}
		}		
		if(!chamado.getUnidade().equals(user.getFuncionario().getUnidadeAtual())) {
			throw new MensagemException("Usuario não tem permissão de acessar este chamado!!!");
		}
		return chamado;
	}

	public void adicionarChamadoNasMensagens(ChamadoTi chamadoTi) {
		Usuario usuario = SessionUsuario.getInstance().getUsuario();		
		for (int i = 0; i < chamadoTi.getMensagens().size(); i++) {
			chamadoTi.getMensagens().get(i).setChamado(chamadoTi);
			chamadoTi.getMensagens().get(i).setData(new Date());
			chamadoTi.getMensagens().get(i).setUsuario(usuario);
		}
	}

	public Page<ChamadoTi> relatorio(PageRequest page) {
		return chamadoTiRepository.findByUnidade_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), page);
	}

	public Collection<ChamadoTi> relatorio(Date dataInicial, Date dataFinal) {
		Collection<ChamadoTi> lista = chamadoTiRepository.relatorio(new ConverteData(dataInicial).getString(),
				new ConverteData(dataFinal).getString(), SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
		if(lista.isEmpty()){
			throw new MensagemException("Não constam movimentações para esta data!!!");
		}
		return lista;
	}

	public Page<ChamadoTi> buscarPorUnidadeComPaginacao(PageRequest pageRequest) {
		return chamadoTiRepository.findByUnidade_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), pageRequest);
	}

	public Collection<ChamadoTi> relatorioPorDataETitulo(Date dataInicial, Date dataFinal, Long idTitulo) {
		Collection<ChamadoTi> lista = chamadoTiRepository.relatorioPorDataETitulo(new ConverteData(dataInicial).getString(),
				new ConverteData(dataFinal).getString(), idTitulo,
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());		
		if(lista.isEmpty()){
			throw new MensagemException("Não constam movimentações para esta data!!!");
		}
		return lista;
	}

	public long count() {
		return chamadoTiRepository.countByStatus(StatusChamado.ABERTO);
	}


	public Result enviarMensagemDeAvisoDeChamadoAberto(Result result, Usuario usuario) {
		Integer count =  (int) chamadoTiRepository.countByUnidadeAndStatus(usuario.getFuncionario().getUnidadeAtual(), StatusChamado.ABERTO);
		result.setValor(count);
		return result;
	}
	

	

	/*@Scheduled(cron = "1 * * * * *", zone= TIME_ZONE)
	public void schedule() {
		Integer count =  (int) chamadoTiRepository.countByStatusAndSilenciar(StatusChamado.ABERTO, false);		
		
		if(count.intValue() > 0) {
			this.messagingTemplate.convertAndSend("/topic/showResult", new Result("Chamado não atendido", count));
		}		 	
		
	}*/

	public Page<ChamadoTi> pageFilter(ChamadoFilter filter, PageRequest page) {
		List<BooleanExpression> geral = filtros(filter);
		if(geral.isEmpty()) {
			return chamadoTiRepository.findAll(page);
		}		
		BooleanExpression addGeral = geral.get(0);
		for(BooleanExpression X : geral) {
			addGeral = addGeral.and(X);
		}		
		return chamadoTiRepository.findAll(addGeral, page);
	}
	
	public Iterable<ChamadoTi> listFilter(ChamadoFilter filter) {
		List<BooleanExpression> geral = filtros(filter);
		if(geral.isEmpty()) {
			return chamadoTiRepository.findAll();
		}		
		BooleanExpression addGeral = geral.get(0);
		for(BooleanExpression X : geral) {
			addGeral = addGeral.and(X);
		}		
		return chamadoTiRepository.findAll(addGeral);
	}
	
	public List<BooleanExpression> filtros(ChamadoFilter filter){
		QChamadoTi qCotacao = QChamadoTi.chamadoTi;
		
		
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		List<BooleanExpression> geral = new ArrayList<>();
		
		BooleanExpression empreendimentoEquals = qCotacao.unidade.eq(user.getFuncionario().getUnidadeAtual());
		geral.add(empreendimentoEquals);
		
		
		if(user.hasRole("CHAMADO_USUARIO")) {
			BooleanExpression userEquals = qCotacao.usuarioSolicitante.eq(user);
			geral.add(userEquals);
		}
		if(filter.getDataCadastroDe() != null && filter.getDataCadastroAte() != null) {
			BooleanExpression dataCriacaoEquals = qCotacao.dataAbertura.between(filter.getDataCadastroDe(), filter.getDataCadastroAte());
			geral.add(dataCriacaoEquals);
		}
		if(filter.getDataFechamentoDe() != null && filter.getDataFechamentoAte() != null) {
			BooleanExpression dataFechamentoEquals = qCotacao.dataFechamento.between(filter.getDataFechamentoDe(), filter.getDataFechamentoAte());
			geral.add(dataFechamentoEquals);
		}
		if(filter.getDescricao() != null) {		
				BooleanExpression textoEquals = qCotacao.texto.containsIgnoreCase(filter.getDescricao());
				geral.add(textoEquals);	
		}
		if(filter.getStatus() != null) {
			BooleanExpression statusEquals = qCotacao.status.eq(filter.getStatus());
			geral.add(statusEquals);
		}
		if(filter.getPrioridade()!= null) {
			BooleanExpression prioridadeEquals = qCotacao.prioridade.eq(filter.getPrioridade());
			geral.add(prioridadeEquals);
		}
		if(filter.getTipoEquipamentos()!= null) {
			BooleanExpression tipoEquals = qCotacao.tipoEquipamento.eq(filter.getTipoEquipamentos());
			geral.add(tipoEquals);
		}
		if(filter.getTitulo() != null) {
			BooleanExpression tituloEquals = qCotacao.titulo.eq(filter.getTitulo());
			geral.add(tituloEquals);
		}	
		if(filter.getMensagem() != null) {
			BooleanExpression mensagensEquals = qCotacao.mensagens.any().texto.containsIgnoreCase(filter.getMensagem());
			geral.add(mensagensEquals);
		}
		return geral;
	}
	

}

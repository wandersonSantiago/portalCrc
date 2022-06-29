package br.com.portalCrc.service.controleIp;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.entity.controleIp.ManutencaoEquipamento;
import br.com.portalCrc.entity.controleIp.ServicosEquipamento;
import br.com.portalCrc.enums.controleIp.StatusEquipamento;
import br.com.portalCrc.enums.controleIp.StatusIp;
import br.com.portalCrc.enums.controleIp.StatusPonto;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.EquipamentoRepositorio;
import br.com.portalCrc.repository.ControleIp.ManutencaoEquipamentoRepository;
import br.com.portalCrc.repository.ControleIp.ServicosEquipamentoRepository;
import br.com.portalCrc.service.diaria.MensagemException;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepositorio equipamentoRepositorio;
	@Autowired
	private IpService ipService;
	@Autowired
	private PontoSevice pontoService;
	@Autowired
	private ServicosEquipamentoRepository servicoEquipamento;
	@Autowired
	private ManutencaoEquipamentoRepository manutencaoEquipamentoRepository;
	
	
	public Collection<Equipamento> lista(){		
		return equipamentoRepositorio.findByUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	public Equipamento buscaPorId(Long id){		
		return equipamentoRepositorio.findOne(id);
	}
	
	public Iterable<Equipamento> findByStatus(StatusEquipamento status) {		
		return equipamentoRepositorio.findByStatusAndUnidade(status, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
		
	public List<ServicosEquipamento> buscarServicos(Long idEquipamento) {
		return servicoEquipamento.findByEquipamento_id(idEquipamento);
	}
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Equipamento equipamento){	
		if(equipamento.getId() != null){
			verificaIp(equipamento);
			verificaPonto(equipamento);
			equipamento.setDataAlteracao(new Date());
		}else{			
			equipamento.setStatus(StatusEquipamento.INATIVO);			
		}
		if(equipamento.getPonto() != null){
			equipamento.getPonto().setStatus(StatusPonto.ATIVO);;
			pontoService.altera(equipamento.getPonto());
		}
		if(equipamento.getIp() != null){
			equipamento.getIp().setStatus(StatusIp.ATIVO);
			ipService.altera(equipamento.getIp());
		}
		equipamento.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
		equipamento.setDataCadastro(new Date());
		equipamento.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario().getId());
										
		equipamentoRepositorio.save(equipamento);	
		
		if( equipamento.getDataAlteracao() == null){
			criarCadastroManutencaoPreventiva(equipamento);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void baixarEquipamento(Equipamento equipamento){
		if(equipamento.getIp() != null){
			equipamento.getIp().setStatus(StatusIp.INATIVO);
			ipService.altera(equipamento.getIp());
		}
		if(equipamento.getPonto() != null){
			equipamento.getPonto().setStatus(StatusPonto.INATIVO);;
			pontoService.altera(equipamento.getPonto());
		}
		equipamento.setIp(null);
		equipamento.setPonto(null);
		equipamento.setStatus(StatusEquipamento.BAIXADO);
		equipamento.setDataCadastro(new Date());
		equipamento.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario().getId());
		
	
		
		inativaManutencaoPreventiva(equipamento);
		equipamentoRepositorio.save(equipamento);
	}
	
	public void verificaIp(Equipamento equipamento){
		if(equipamento.getId() != null){
			Equipamento e = equipamentoRepositorio.findOne(equipamento.getId());
			if(e.getIp() != null){
				e.getIp().setStatus(StatusIp.INATIVO);
			}			
		}if(equipamento.getIp() != null){
			equipamento.getIp().setStatus(StatusIp.ATIVO);
			ipService.altera(equipamento.getIp());
		}		
	}
	public void verificaPonto(Equipamento equipamento){
		if(equipamento.getPonto() != null){
			Equipamento e = equipamentoRepositorio.findOne(equipamento.getId());
			if(e.getPonto() != null){
				e.getPonto().setStatus(StatusPonto.INATIVO);;
			}
		}if(equipamento.getPonto() != null){
			equipamento.getPonto().setStatus(StatusPonto.ATIVO);;
			equipamento.setStatus(StatusEquipamento.ATIVO);
			pontoService.altera(equipamento.getPonto());
		}else{
			equipamento.setStatus(StatusEquipamento.INATIVO);
		}
	}

	@Transactional(readOnly = false)
	public void criarCadastroManutencaoPreventiva(Equipamento equipamento){
		ManutencaoEquipamento manutencao = new ManutencaoEquipamento();
		ManutencaoEquipamentoService cadastrarPreventiva = new ManutencaoEquipamentoService();
		manutencao.setDataPreventiva(cadastrarPreventiva.dataPreventiva());
		manutencao.setEquipamento(equipamento);
		manutencao.setStatus(true);
		manutencao.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario().getId());
		manutencao.setDescricao("Preventiva Padrão");
		manutencaoEquipamentoRepository.save(manutencao);
		
	}
	@Transactional(readOnly = false)
	public void inativaManutencaoPreventiva(Equipamento equipamento){
		ManutencaoEquipamento item = manutencaoEquipamentoRepository.findTop1ByEquipamento_idOrderByIdDesc(equipamento.getId());
			item.setStatus(false);
		manutencaoEquipamentoRepository.save(item);
		
	}

	public Equipamento findByPatrimonio(String patrimonio) {
		Equipamento verifica = equipamentoRepositorio.findByPatrimonioIgnoreCase(patrimonio);
		
		if(verifica != null){
			throw new MensagemException("Este Patrimonio já esta Cadastrado");
		}
		return verifica;
	}

	
	
	//busca de equipamento por tipo
	public List<Equipamento> buscarPorTipo(TipoEquipamentoEnum tipo) {
		return equipamentoRepositorio.findByTipoEquipamento(tipo);
	}

	

}

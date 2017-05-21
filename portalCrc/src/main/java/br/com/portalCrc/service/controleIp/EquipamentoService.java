package br.com.portalCrc.service.controleIp;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.enums.controleIp.StatusEquipamento;
import br.com.portalCrc.enums.controleIp.StatusIp;
import br.com.portalCrc.enums.controleIp.StatusPonto;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.EquipamentoRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepositorio equipamentoRepositorio;
	@Autowired
	private IpService ipService;
	@Autowired
	private PontoSevice pontoService;
	
	
	
	public Collection<Equipamento> lista(){		
		return equipamentoRepositorio.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	
	public Equipamento buscaPorId(Long id){		
		return equipamentoRepositorio.findOne(id);
	}
	
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Equipamento equipamento){	
		if(equipamento.getId() != null){
			verificaIp(equipamento);
			verificaPonto(equipamento);
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
		equipamento.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		equipamento.setDataCadastro(new Date());
		equipamento.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
				
		equipamentoRepositorio.save(equipamento);
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
		equipamento.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
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


	public Iterable<Equipamento> findByStatus(StatusEquipamento status) {		
		return equipamentoRepositorio.findByStatusAndUnidade_id(status, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

}

package br.com.portalCrc.service.controleIp;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.entity.controleIp.Ponto;
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
		}
		if(equipamento.getPonto() != null){
			equipamento.getPonto().setEmUso(true);
			pontoService.altera(equipamento.getPonto());
		}
		if(equipamento.getIp() != null){
			equipamento.getIp().setEmUso(true);
			ipService.altera(equipamento.getIp());
		}
		equipamento.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		equipamento.setDataCadastro(new Date());
		equipamento.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		equipamento.setAtivo(true);
		
		equipamentoRepositorio.save(equipamento);
	}
	
	
	public void verificaIp(Equipamento equipamento){
		if(equipamento.getId() != null){
			Equipamento e = equipamentoRepositorio.findOne(equipamento.getId());
			if(e.getIp() != null){
				e.getIp().setEmUso(false);
			}			
		}if(equipamento.getIp() != null){
			equipamento.getIp().setEmUso(true);
			ipService.altera(equipamento.getIp());
		}		
	}
	public void verificaPonto(Equipamento equipamento){
		if(equipamento.getPonto() != null){
			Equipamento e = equipamentoRepositorio.findOne(equipamento.getId());
			if(e.getPonto() != null){
				e.getPonto().setEmUso(false);
			}			
		}if(equipamento.getPonto() != null){
			equipamento.getPonto().setEmUso(true);
			pontoService.altera(equipamento.getPonto());
		}
	}

}

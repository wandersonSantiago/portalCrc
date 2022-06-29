package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Ponto;
import br.com.portalCrc.enums.controleIp.StatusPonto;
import br.com.portalCrc.enums.controleIp.StatusPortaSwitch;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.PontoRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class PontoSevice {
	
	@Autowired
	private PontoRepositorio pontoRepositorio;
	@Autowired
	private PortaSwitchService portaSwitchService;

	
	
	public Collection<Ponto> lista(){
		return pontoRepositorio.findByUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	
	public Ponto buscaPorId(Long id){
		return pontoRepositorio.findOne(id);
		
	}

	@Transactional(readOnly = false)
	public void salvaOuAltera(Ponto ponto){
	if(ponto.getId() != null){
		verificaPortaSwicth(ponto);
	}	
		ponto.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario().getId());
		ponto.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
		if(ponto.getPortaSwitch() != null){
			ponto.setStatus(StatusPonto.ATIVO);
			ponto.getPortaSwitch().setStatus(StatusPortaSwitch.ATIVO);;
			ponto.getPortaSwitch().setSwitchs(ponto.getSwitchs());
			portaSwitchService.salvaOuAltera(ponto.getPortaSwitch());	
		}else{
			ponto.setStatus(StatusPonto.INATIVO);
		}
		pontoRepositorio.save(ponto);		
	}
	
	@Transactional(readOnly = false)
	public void altera(Ponto ponto) {
		pontoRepositorio.save(ponto);
		
	}

	
	public void verificaPortaSwicth(Ponto ponto){
		
		if(ponto.getPortaSwitch() != null){
			Ponto p = pontoRepositorio.findOne(ponto.getId());	
			if(p.getPortaSwitch() != null){
				p.getPortaSwitch().setStatus(StatusPortaSwitch.INATIVO);;
			}			
		}else{
			Ponto p = pontoRepositorio.findOne(ponto.getId());	
			if(p.getPortaSwitch() != null){
				p.getPortaSwitch().setStatus(StatusPortaSwitch.INATIVO);
			}
		}
	}


	public Iterable<Ponto> listaPorStatus(StatusPonto status) {
		return pontoRepositorio.findByStatusAndUnidade(status, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
}
package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Ponto;
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
		return pontoRepositorio.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getUnidade().getId());
	}
	
	
	public Ponto buscaPorId(Long id){
		return pontoRepositorio.findOne(id);
		
	}

	@Transactional(readOnly = false)
	public void salvaOuAltera(Ponto ponto){
	if(ponto.getId() != null){
		verificaPortaSwicth(ponto);
	}else{
		ponto.setEmUso(false);
	}
		ponto.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		ponto.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		if(ponto.getPortaSwitch() != null){
			ponto.getPortaSwitch().setEmUso(true);
			ponto.getPortaSwitch().setSwitchs(ponto.getSwitchs());
			portaSwitchService.salvaOuAltera(ponto.getPortaSwitch());	
		}
		pontoRepositorio.save(ponto);		
	}
	
	@Transactional(readOnly = false)
	public void altera(Ponto ponto) {
		pontoRepositorio.save(ponto);
		
	}


	public Iterable<Ponto> listaEmUso(boolean b) {
		return pontoRepositorio.findAllByEmUso(b);
	}
	
	public void verificaPortaSwicth(Ponto ponto){
		
		if(ponto.getPortaSwitch() != null){
			Ponto p = pontoRepositorio.findOne(ponto.getId());	
			if(p.getPortaSwitch() != null){
				p.getPortaSwitch().setEmUso(false);
			}			
		}else{
			Ponto p = pontoRepositorio.findOne(ponto.getId());	
			if(p.getPortaSwitch() != null){
				p.getPortaSwitch().setEmUso(false);
			}
		}
	}
}
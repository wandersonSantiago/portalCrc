package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.SwitchPonto;
import br.com.portalCrc.repository.ControleIp.SwitchPontoRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class SwitchPontoService {
	
	
	@Autowired
	private SwitchPontoRepositorio switchPontoRepositorio;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(SwitchPonto ponto){
		switchPontoRepositorio.save(ponto);
		
	}
	
	
	public Collection<SwitchPonto> lista(){
		return switchPontoRepositorio.findAll();
	}
	
	
	public SwitchPonto buscaPorId(Long id){
		return switchPontoRepositorio.findOne(id);
		
	}

}

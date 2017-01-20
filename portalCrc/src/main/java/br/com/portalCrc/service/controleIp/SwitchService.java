package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Switch;
import br.com.portalCrc.repository.ControleIp.SwitchRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class SwitchService {
	
	@Autowired
	private SwitchRepositorio switchRepositorio;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Switch Switch){
		switchRepositorio.save(Switch);
		
	}
	
	
	public Collection<Switch> lista(){
		return switchRepositorio.findAll();
	}
	
	
	public Switch buscaPorId(Long id){
		return switchRepositorio.findOne(id);
		
	}
}
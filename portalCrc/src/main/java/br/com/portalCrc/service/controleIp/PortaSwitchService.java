package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.PortaSwitch;
import br.com.portalCrc.repository.ControleIp.PortaSwitchRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class PortaSwitchService {

	
	@Autowired
	private PortaSwitchRepositorio portaSwitchRepositorio;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(PortaSwitch porta){
		portaSwitchRepositorio.save(porta);
		
	}
	
	
	public Collection<PortaSwitch> lista(){
		return portaSwitchRepositorio.findAll();
	}
	
	
	public PortaSwitch buscaPorId(Long id){
		return portaSwitchRepositorio.findOne(id);
		
	}


	public Iterable<PortaSwitch> listaPortaLivre(Long id) {
		return portaSwitchRepositorio.listaPortaLivre(id);
	}
}

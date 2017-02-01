package br.com.portalCrc.service.controleIp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.PortaSwitch;
import br.com.portalCrc.entity.controleIp.Switch;
import br.com.portalCrc.enums.controleIp.StatusSwitch;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.SwitchRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class SwitchService {
	
	@Autowired
	private SwitchRepositorio switchRepositorio;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Switch switchs){
		
		switchs.setDataCadastro(new Date());
		switchs.setStatus(StatusSwitch.ATIVO);
		switchs.setPortaSwitch(listaPortas(switchs));
		
		switchRepositorio.save(switchs);
		
	}
	
	
	public Collection<Switch> lista(){
		return switchRepositorio.findAll();
	}
	
	
	public Switch buscaPorId(Long id){
		return switchRepositorio.findOne(id);
		
	}
	
	public List<PortaSwitch> listaPortas(Switch switchs){
		
		List<PortaSwitch> lista  = new ArrayList<>();
		
		for(int i = 0 ; i < switchs.getQtdPortas() ; i++){
			PortaSwitch lista2 = new PortaSwitch();
			lista2.setDataCadastro(new Date());
			lista2.setNumero(i);
			lista2.setStatus(StatusSwitch.INATIVO);
			lista2.setDescricao("OK");
			lista2.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			lista2.setSwitchs(switchs);
			lista.add(lista2);
		}
		return lista;
	}
}
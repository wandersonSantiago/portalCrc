package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.repository.ControleIp.EquipamentoRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class EquipamentoService {
	
	@Autowired
	private EquipamentoRepositorio equipamentoRepositorio;
	
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Equipamento equipamento){		
		equipamentoRepositorio.save(equipamento);
	}
	
	
	public Collection<Equipamento> lista(){		
		return equipamentoRepositorio.findAll();
	}
	
	
	public Equipamento buscaPorId(Long id){		
		return equipamentoRepositorio.findOne(id);
	}
	

}

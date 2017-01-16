package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Ponto;
import br.com.portalCrc.repository.ControleIp.PontoRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class PontoSevice {
	
	@Autowired
	private PontoRepositorio pontoRepositorio;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Ponto ponto){
		pontoRepositorio.save(ponto);
		
	}
	
	
	public Collection<Ponto> lista(){
		return pontoRepositorio.findAll();
	}
	
	
	public Ponto buscaPorId(Long id){
		return pontoRepositorio.findOne(id);
		
	}
}

package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.repository.ControleIp.IpRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class IpService {

	@Autowired
	private IpRepositorio ipRepositorio;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Ip ip){
		ipRepositorio.save(ip);
				
	}
	
	public Collection<Ip> lista(){
		return ipRepositorio.findAll();
	}
	
	public Ip buscaPorId(Long id){
		return ipRepositorio.findOne(id);
	}
}

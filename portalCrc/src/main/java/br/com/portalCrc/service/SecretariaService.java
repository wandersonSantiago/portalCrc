package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Secretaria;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.SecretariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SecretariaService {
	
	@Autowired
	private SecretariaRepository secretariaReqpository;
	
	@Transactional(readOnly = false)
	public void salvarEditar (Secretaria secretaria){
		secretaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		secretaria.setDataCadastro(new Date());
		secretariaReqpository.save(secretaria);
	}
	
	public Collection<Secretaria> lista(){
		return secretariaReqpository.findAll();
	}
	
	public Secretaria buscaPorID(Long id){
		return secretariaReqpository.findOne(id);
	}
}

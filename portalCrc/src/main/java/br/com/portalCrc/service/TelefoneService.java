package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Telefone;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.TelefoneRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class TelefoneService {

	
	@Autowired 
	private TelefoneRepository telefoneRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Telefone telefone){
		telefone.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		telefone.setDataCadastro(new Date());
		telefone.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		telefoneRepository.save(telefone);
	}
	
	public Collection<Telefone> lista(){
		return telefoneRepository.findAll();
	}
	
	public Telefone buscaPorId(Long id){
		return telefoneRepository.findOne(id);
	}

	public List<Telefone> buscarTelefonePorUnidade(Long id) {		
		return telefoneRepository.listaTelefoneRepository(id);
	}
}

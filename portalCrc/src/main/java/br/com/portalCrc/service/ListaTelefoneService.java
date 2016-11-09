package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.ListaTelefone;
import br.com.portalCrc.repository.ListaTelefoneRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ListaTelefoneService {

	
	@Autowired 
	private ListaTelefoneRepository listaTelefoneRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(ListaTelefone listaTelefone){
		listaTelefoneRepository.save(listaTelefone);
	}
	
	public Collection<ListaTelefone> lista(){
		return listaTelefoneRepository.findAll();
	}
	
	public ListaTelefone buscaPorId(Long id){
		return listaTelefoneRepository.findOne(id);
	}
}

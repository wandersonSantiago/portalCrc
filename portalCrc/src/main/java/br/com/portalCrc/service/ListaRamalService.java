package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.ListaRamal;
import br.com.portalCrc.repository.ListaRamalRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ListaRamalService {
	
	@Autowired 
	private ListaRamalRepository ListaRamalRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(ListaRamal listaRamal){
		ListaRamalRepository.save(listaRamal);
	}
	
	public Collection<ListaRamal> lista(){
		return ListaRamalRepository.findAll();
	}
	
	public ListaRamal buscaPorId(Long id){
		return ListaRamalRepository.findOne(id);
	}

}

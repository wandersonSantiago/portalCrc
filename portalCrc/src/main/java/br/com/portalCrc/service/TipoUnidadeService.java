package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.TipoUnidade;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.repository.TipoUnidadeRepository;

@Service
@Transactional(readOnly = true, propagation =Propagation.REQUIRED)
public class TipoUnidadeService {

	
	@Autowired
	private TipoUnidadeRepository tipoUnidadeRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(TipoUnidade tipoUnidade){
		tipoUnidadeRepository.save(tipoUnidade);
	}
	
	public Collection<TipoUnidade> lista(){
		return tipoUnidadeRepository.findAll();
	}
	
	public TipoUnidade buscaPorId(Long id){
		return tipoUnidadeRepository.findOne(id);
	}
}

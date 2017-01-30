package br.com.portalCrc.service.diaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.repository.diaria.DiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DiariaService {

	@Autowired
	private DiariaRepository diariaRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Diaria diaria){
		diariaRepository.save(diaria);
	}
	
	public Diaria buscaPorId(Long id){
		return diariaRepository.findOne(id);
	}
	
	public List<Diaria> lista(){
		return diariaRepository.findAll();
	}
}

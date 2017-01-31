package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.DiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DiariaService {

	@Autowired
	private DiariaRepository diariaRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Diaria diaria){
		diaria.setDataCadastro(new Date());
		diaria.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		diaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		diariaRepository.save(diaria);
	}
	
	public Diaria buscaPorId(Long id){
		return diariaRepository.findOne(id);
	}
	
	public List<Diaria> listaUnidade(){
		return diariaRepository.listaUnidade(SessionUsuario.getInstance().getUsuario().getUnidade().getId());
	}
	
	public List<Diaria> listaCoordenadoria(){
		return diariaRepository.listaCoordenadoria(SessionUsuario.getInstance().getUsuario().getUnidade().getCoordenadoria().getId());
	}
	public List<Diaria> lista(){
		return diariaRepository.findAll();
	}
}

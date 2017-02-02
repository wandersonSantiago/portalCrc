package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.DiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DiariaService {

	@Autowired
	private DiariaRepository diariaRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Diaria diaria){
		diaria.setDataAbertura(new Date());
		diaria.setStatus(StatusDiariaEnum.ABERTO);
		diaria.setUnidadeCadastro(SessionUsuario.getInstance().getUsuario().getUnidade());
		diaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		diariaRepository.save(diaria);
	}
	
	public Diaria buscaPorId(Long id){
		return diariaRepository.findOne(id);
	}
	
	
	public List<Diaria> diariasEmAberto(){
		return diariaRepository.diariasEmAberto();
	}
	public List<Diaria> lista(){
		return diariaRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void encerrar(Diaria diaria) {
		
		diaria.setDataFechamento(new Date());
		diaria.setStatus(StatusDiariaEnum.FECHADO);
		diariaRepository.save(diaria);
	}
}

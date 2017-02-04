package br.com.portalCrc.service.diaria;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public void salvaOuAltera(Diaria diaria) {
				
		if(verificaSeExisteMesDeDiariaNoAno(diaria) == false){
			System.out.println(diaria.getMes());
			diaria.setDataAbertura(new Date());
			diaria.setStatus(StatusDiariaEnum.ABERTO);
			//diaria.setUnidadeCadastro(SessionUsuario.getInstance().getUsuario().getUnidade());
			//diaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			diariaRepository.save(diaria);
		}else{			
			throw new DiariaException("o mês de " + diaria.getMes() + " consta aberto no ano " + anoAtual());
		}
	}
	
	public Diaria buscaPorId(Long id){
		return diariaRepository.findOne(id);
	}
	
	
	public List<Diaria> diariasEmAberto(){
		return diariaRepository.diariasEmAberto();
	}
	public Page<Diaria> lista(PageRequest pageRequest){
		return diariaRepository.findAll(pageRequest);
	}
	
	@Transactional(readOnly = false)
	public void encerrar(Diaria diaria) {
		
		diaria.setDataFechamento(new Date());
		diaria.setStatus(StatusDiariaEnum.FECHADO);
		diariaRepository.save(diaria);
	}
	
	public boolean verificaSeExisteMesDeDiariaNoAno(Diaria diaria){
		
		boolean isIgual = false;		
		
		int anoAtual = Integer.parseInt(anoAtual());
	
		List<Diaria> lista = diariaRepository.findByDataAbertura(anoAtual);
		
			for(int i = 0; i < lista.size() ; i++){					
				if(lista.get(i).getMes() == diaria.getMes()){
					isIgual =  true;
					i = lista.size() + 1;
				}					
			}		
		return isIgual;
	}
	
	public String anoAtual(){
		
		 Date dataAtual = new Date();
		 SimpleDateFormat formataAno = new SimpleDateFormat("yyyy");		
		 String ano = formataAno.format(dataAtual);
		 
		 return ano;
	}
}

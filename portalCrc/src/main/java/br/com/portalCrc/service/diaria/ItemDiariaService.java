package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.ItemDiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ItemDiariaService {
	

	@Autowired
	private ItemDiariaRepository itemdiariaRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(ItemDiaria itemdiaria){
		itemdiaria.setDataCadastro(new Date());
		itemdiaria.setUnidade(SessionUsuario.getInstance().getUsuario().getUnidade());
		itemdiaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		itemdiariaRepository.save(itemdiaria);
	}
	
	public ItemDiaria buscaPorId(Long id){
		return itemdiariaRepository.findOne(id);
	}
	
	public List<ItemDiaria> listaUnidade(Long id){
		return itemdiariaRepository.listaUnidade(SessionUsuario.getInstance().getUsuario().getUnidade().getId(), id);
	}
	
	public List<ItemDiaria> listaCoordenadoria(Long id){
		return itemdiariaRepository.findByUnidadeCoordenadoria_idAndDiaria_id(SessionUsuario.getInstance().getUsuario().getUnidade().getCoordenadoria().getId(), id);
	}
	public List<ItemDiaria> lista(){
		return itemdiariaRepository.findAll();
	}

}

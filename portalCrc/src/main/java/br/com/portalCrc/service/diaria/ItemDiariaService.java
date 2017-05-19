package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.ItemDiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ItemDiariaService {
	

	@Autowired
	private ItemDiariaRepository itemDiariaRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(ItemDiaria itemDiaria){
		itemDiaria.setDataCadastro(new Date());
		if(itemDiaria.getFuncionarioDiaria().getDiaria().getStatus()  == StatusDiariaEnum.ABERTO){
			itemDiariaRepository.save(itemDiaria);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void altera(ItemDiaria itemDiaria){
		if(itemDiaria.getFuncionarioDiaria().getDiaria().getStatus()  == StatusDiariaEnum.ABERTO){
			itemDiariaRepository.save(itemDiaria);
		}
		
	}
	
	public ItemDiaria buscaPorId(Long id){
		return itemDiariaRepository.findOne(id);
	}
	
	public List<ItemDiaria> listaUnidade(Long id){
		return itemDiariaRepository.listaUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
	}
	
	public List<ItemDiaria> listaCoordenadoria(Long id){
		return itemDiariaRepository.findByUnidadeCoordenadoria_idAndDiaria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId(), id);
	}
	public List<ItemDiaria> lista(){
		return itemDiariaRepository.findAll();
	}

	public Iterable<ItemDiaria> listaSecretaria(Long id) {		
		return itemDiariaRepository.listaSecretaria(id);
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		itemDiariaRepository.delete(id);
		
	}

	

}

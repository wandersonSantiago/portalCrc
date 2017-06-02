package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.FuncionarioDiariaRepository;
import br.com.portalCrc.repository.diaria.ItemDiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ItemDiariaService {
	

	@Autowired
	private ItemDiariaRepository itemDiariaRepository;
	@Autowired
	private FuncionarioDiariaRepository funcionarioDiariaRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(ItemDiaria itemDiaria){
		
		FuncionarioDiaria funcionarioDiaria =  funcionarioDiariaRepository.findById(itemDiaria.getFuncionarioDiaria().getId());
				
	
		CalculaValor calcula = new CalculaValor();
		
		itemDiaria.setDataCadastro(new Date());
		
		BigDecimal valorTotalItem = new BigDecimal(0);
		BigDecimal valorTotalDiaria = funcionarioDiaria.getTotalValorDiaria();
		
		int count = calcula.quantidadePernoite(itemDiaria);
		
		valorTotalItem = valorTotalItem.add(calcula.valorPernoite(count, itemDiaria));
		itemDiaria.setValorDiaria(valorTotalItem);
		
		valorTotalDiaria = valorTotalDiaria.add(valorTotalItem);
		funcionarioDiaria.setTotalValorDiaria(valorTotalDiaria);
		
		if(itemDiaria.getFuncionarioDiaria().getDiaria().getStatus()  == StatusDiariaEnum.ABERTO){
			itemDiariaRepository.save(itemDiaria);
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}else{
			throw new MensagemException("Não foi possivel realizar este lançamento, Este mês esta encerrado!!!");
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
		List<ItemDiaria> lista 	= itemDiariaRepository.listaUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
		
		if(lista.isEmpty() || lista == null){
			throw new MensagemException("Unidade não tem lançameto nesta diaria! ");
		}
		
		return lista;
	}
	
	public List<ItemDiaria> listaCoordenadoria(Long id){
		List<ItemDiaria> lista 	=  itemDiariaRepository.findByUnidadeCoordenadoria_idAndDiaria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId(), id);
		
		if(lista.isEmpty() || lista == null){
			throw new MensagemException("Coordenadoria não tem lançameto nesta diaria! ");
		}
		
		return lista;
		
	}
	public List<ItemDiaria> lista(){
		return itemDiariaRepository.findAll();
	}

	public List<ItemDiaria> listaSecretaria(Long id) {		
		List<ItemDiaria> lista 	=  itemDiariaRepository.listaSecretaria(id);
		if(lista.isEmpty()|| lista == null){
			throw new MensagemException("Secretaria não tem lançameto nesta diaria! ");
		}
		
		return lista;
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		itemDiariaRepository.delete(id);
		
	}

	public Iterable<ItemDiaria> findByFuncionarioDiaria_id(Long id) {
		return itemDiariaRepository.findByFuncionarioDiaria_id(id);
	}

	

}

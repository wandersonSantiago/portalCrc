package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;
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
		
		if(itemDiaria.isAnalizado()){
			throw new MensagemException("Lançamento encerrado!!!");
		}
		FuncionarioDiaria funcionarioDiaria =  funcionarioDiariaRepository.findById(itemDiaria.getFuncionarioDiaria().getId());				
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		itemDiaria.setDataCadastro(new Date());		
		itemDiaria.setUsuarioCadastro(user);
		
		
		CalculaValor calcula = new CalculaValor();
		int count = calcula.quantidadePernoite(itemDiaria);
		
		if(count > 0) {
			itemDiaria.setQtdPernoite(count);
		}
		
		BigDecimal valorTotalItem = new BigDecimal(0);
		BigDecimal valorTotalDiaria = funcionarioDiaria.getTotalValorDiaria();
		
		valorTotalItem = valorTotalItem.add(calcula.valorPernoite(count, itemDiaria));
		itemDiaria.setValorDiaria(valorTotalItem);
		
		somaValorTotalDiaria(valorTotalDiaria, valorTotalItem , funcionarioDiaria , itemDiaria);		 		
	}
	
	
	@Transactional(readOnly = false)
	public void altera(ItemDiaria itemDiaria){
		FuncionarioDiaria funcionarioDiaria =  funcionarioDiariaRepository.findById(itemDiaria.getFuncionarioDiaria().getId());
		ItemDiaria item = itemDiariaRepository.findOne(itemDiaria.getId());
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		CalculaValor calcula = new CalculaValor();
		
		itemDiaria.setDataAlteracao(new Date());
		itemDiaria.setUsuarioAlteracao(user);
		
		BigDecimal valorTotalItem = new BigDecimal(0);
		BigDecimal valorTotalDiaria = funcionarioDiaria.getTotalValorDiaria();
		
		int count = calcula.quantidadePernoite(itemDiaria);
		
		if(count > 0) {
			itemDiaria.setQtdPernoite(count);
		}
		
		valorTotalItem = valorTotalItem.add(calcula.valorPernoite(count, itemDiaria));
		itemDiaria.setValorDiaria(valorTotalItem);
		
		valorTotalDiaria = valorTotalDiaria.subtract(item.getValorDiaria());		
		
		somaValorTotalDiaria(valorTotalDiaria, valorTotalItem , funcionarioDiaria , itemDiaria);
	}
	
	
	@Transactional(readOnly = false)
	public void somaValorTotalDiaria(BigDecimal valorTotalDiaria , BigDecimal valorTotalItem, FuncionarioDiaria funcionarioDiaria, ItemDiaria itemDiaria ){
		
		valorTotalDiaria = valorTotalDiaria.add(valorTotalItem);
		BigDecimal maximoDiaria = new BigDecimal(0);
		BigDecimal glosada = new BigDecimal(0);
		BigDecimal salario = funcionarioDiaria.getContaFuncionario().getSalarioAtual();
		BigDecimal porcentagem = new BigDecimal(funcionarioDiaria.getContaFuncionario().getLimiteCemPorCento());
		BigDecimal divisor = new BigDecimal(100);
		
		maximoDiaria = salario.multiply(porcentagem).divide(divisor);
		
		if(valorTotalDiaria.compareTo(maximoDiaria) == 1){
			if(funcionarioDiaria.getGlosada() != null){
				glosada = glosada.add(funcionarioDiaria.getGlosada());
				
				glosada = glosada.add(valorTotalDiaria.subtract(maximoDiaria));   
			}else{
				glosada = valorTotalDiaria.subtract(maximoDiaria);
			}			
			funcionarioDiaria.setGlosada(glosada);
			funcionarioDiaria.setTotalValorDiaria(maximoDiaria);
			
			
		}else{
			funcionarioDiaria.setTotalValorDiaria(valorTotalDiaria);
		}	
		
		
		if(itemDiaria.getFuncionarioDiaria().getDiaria().getStatus()  == StatusDiariaEnum.ABERTO){
			itemDiariaRepository.save(itemDiaria);
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}else{
			throw new MensagemException("Não foi possivel realizar este lançamento, Este mês esta encerrado!!!");
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
		
		ItemDiaria item = itemDiariaRepository.findOne(id);
		
		FuncionarioDiaria funcionarioDiaria =  funcionarioDiariaRepository.findById(item.getFuncionarioDiaria().getId());
		
		BigDecimal glosada = funcionarioDiaria.getGlosada();
		BigDecimal itemValor = item.getValorDiaria();
		BigDecimal valorTotalDiaria = funcionarioDiaria.getTotalValorDiaria();
		
		if(glosada != null){
			if(itemValor.compareTo(glosada)==1){
				funcionarioDiaria.setGlosada(new BigDecimal(0));				
				BigDecimal diferenca = itemValor.subtract(glosada);
				valorTotalDiaria = valorTotalDiaria.subtract(diferenca);
				
			}else{
				BigDecimal diferenca = glosada.subtract(itemValor);
				funcionarioDiaria.setGlosada(diferenca);
				
			}
		}else{
			valorTotalDiaria = valorTotalDiaria.subtract(item.getValorDiaria());
		}
		
		
		funcionarioDiaria.setTotalValorDiaria(valorTotalDiaria);
		
		funcionarioDiariaRepository.save(funcionarioDiaria);
		itemDiariaRepository.delete(item.getId());
		
	}

	public Iterable<ItemDiaria> findByFuncionarioDiaria_id(Long id) {
		return itemDiariaRepository.findByFuncionarioDiaria_id(id);
	}

	@Transactional(readOnly = false)
	public void analizado(Long idItem) {		
		ItemDiaria item = itemDiariaRepository.findOne(idItem);
		item.setAnalizado(true);
	}


	public Iterable<ItemDiaria> findByFuncionarioDiaria_idAndTipo(Long idFuncionario, TipoDiariaEnum tipo) {
		return itemDiariaRepository.findByFuncionarioDiaria_idAndTipo(idFuncionario, tipo);
	}


	@Transactional(readOnly = false)
	public void retorno(Long idItem) {
		ItemDiaria item = itemDiariaRepository.findOne(idItem);
		
		if(item.isRetorno()){
			item.setRetorno(false);
		}else{
			item.setRetorno(true);
		}
		
		
	}



	

}

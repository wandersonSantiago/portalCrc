package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ItemDashDTO;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.FuncionarioDiariaRepository;
import br.com.portalCrc.repository.diaria.ItemDiariaRepository;
import br.com.portalCrc.util.DateUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ItemDiariaService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ItemDiariaRepository itemDiariaRepository;
	@Autowired
	private FuncionarioDiariaRepository funcionarioDiariaRepository;
	@Autowired
	private DiariaService diariaService;
	
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
		
		log.info("Lançamento de diaria realizado pelo usuario " + itemDiaria.getUsuarioCadastro().getLogin() + " para o funcionario " + itemDiaria.getFuncionarioDiaria().getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto());
	}
	
	
	@Transactional(readOnly = false)
	public void altera(ItemDiaria itemDiaria){
		FuncionarioDiaria funcionarioDiaria =  funcionarioDiariaRepository.findById(itemDiaria.getFuncionarioDiaria().getId());
		ItemDiaria item = itemDiariaRepository.findOne(itemDiaria.getId());
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		CalculaValor calcula = new CalculaValor();
		
		itemDiaria.setUsuarioAlteracao(user);
		
		BigDecimal valorTotalItem = new BigDecimal(0);
		BigDecimal valorTotalDiaria = funcionarioDiaria.getTotalValorDiaria();
		
		int count = calcula.quantidadePernoite(itemDiaria);
		
		if(count > 0) {
			itemDiaria.setQtdPernoite(count);
		}
		
		valorTotalItem = valorTotalItem.add(calcula.valorPernoite(count, itemDiaria));
		itemDiaria.setValorDiaria(valorTotalItem);
		
		if(item.getValorDiaria().compareTo(valorTotalDiaria) == 1){
			throw new MensagemException("Lançamento não realizado, valor do item esta maior que o valor total, favor entrar em contato com o suporte!!!");
		}
		
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
	
	public List<ItemDiaria> listaCoordenadoria(MesDiariaEnum mes){
		List<ItemDiaria> lista 	=  itemDiariaRepository.findByUnidadeCoordenadoria_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId(), mes);
		
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
		
		
		if(item.getFuncionarioDiaria().getDiaria().getStatus() == StatusDiariaEnum.FECHADO){
			throw new MensagemException("Não foi possivel realizar este lançamento, Este mês esta encerrado!!!");
		}
		
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

	public List<ItemDiaria> findByFuncionarioDiaria_id(Long id) {
		return itemDiariaRepository.findByFuncionarioDiaria_id(id);
	}

	@Transactional(readOnly = false)
	public void analizado(Long idItem) {		
		ItemDiaria item = itemDiariaRepository.findOne(idItem);	
		if(item.getFuncionarioDiaria().getDiaria().getStatus() == StatusDiariaEnum.FECHADO){
			throw new MensagemException("Não foi possivel realizar este lançamento, Este mês esta encerrado!!!");
		}
		FuncionarioDiaria diariaFuncionario = funcionarioDiariaRepository.findById(item.getFuncionarioDiaria().getId());		
		
		BigDecimal salario = diariaFuncionario.getSalarioAtual();
		BigDecimal porcentagem = new BigDecimal(diariaFuncionario.getLimiteCemPorCento());
		BigDecimal divisor = new BigDecimal(100);
		
		BigDecimal totalItem = item.getValorDiaria();
		BigDecimal totalPago = diariaFuncionario.getTotalPago();
				

		BigDecimal maximoDiaria = salario.multiply(porcentagem).divide(divisor);
		
		totalPago = totalPago.add(totalItem);
		
		if(totalPago.compareTo(maximoDiaria) == 1){			
			totalPago = maximoDiaria;			
		}
		
		diariaFuncionario.setTotalPago(totalPago);
		
		item.setAnalizado(true);
	}


	public List<ItemDiaria> findByFuncionarioDiaria_idAndTipo(Long idFuncionario, TipoDiariaEnum tipo) {
		List<ItemDiaria> list = itemDiariaRepository.findByFuncionarioDiaria_idAndTipo(idFuncionario, tipo);
		if(list.isEmpty()) {
			 list = itemDiariaRepository.findByFuncionarioDiaria_idAndTipo(idFuncionario, TipoDiariaEnum.SEGURANCA);
			 if(list.isEmpty()){
				//	throw new MensagemException("Funcionario não tem diarias!!!");
				}
		}
		return list;
	}
	
	public Double somaValorItemDiariaPorIdDiariaETipo(Long idDiaria, TipoDiariaEnum tipo) {
		return itemDiariaRepository.findByFuncionarioDiariaDiaria_idAndTipo(idDiaria, tipo);		
		
	}

	
	/**
	 * @TODO gambiarra para fazer que o calculo da diaria do mesmo dia 
	 * seja convertido em um retorno
	 * essa é uma gambiarra, que tem que ser alterada o mais rapido possivel, ta muito zuado!!
	 * @param idItem - recebe o id do item da diaria a qual a gambiarra ira executar as alterações
	 */
	@Transactional(readOnly = false)
	public void retorno(Long idItem) {
		ItemDiaria item = itemDiariaRepository.findOne(idItem);
		
		if(item.getFuncionarioDiaria().getDiaria().getStatus() == StatusDiariaEnum.FECHADO){
			throw new MensagemException("Não foi possivel realizar este lançamento, Este mês esta encerrado!!!");
		}
		
		if(item.isRetorno()){
			if(item.getQtdRegressoAposDezenove() != null) {
				item.setQtdDeslocamentoMaisDeDoze(1);
				item.setValorDeslocamentoMaisDeDoze(item.getValorTotalRegressoAposDezenove());
				item.setQtdRegressoAposDezenove(null);
				item.setValorTotalRegressoAposDezenove(null);
				
			}else {
				item.setQtdDeslocamentoDasSeisAsDoze(1);
				item.setValorTotalDeslocamentoDasSeisAsDoze(item.getValorTotalRegressoTrezeAsDezenove());
				item.setQtdRegressoTrezeAsDezenove(null);			
				item.setValorTotalRegressoTrezeAsDezenove(null);
			}
			item.setRetorno(false);
		}else {
		if(item.getQtdDeslocamentoMaisDeDoze() != null) {
			item.setQtdRegressoAposDezenove(1);
			item.setValorTotalRegressoAposDezenove(item.getValorDeslocamentoMaisDeDoze());
			item.setQtdDeslocamentoMaisDeDoze(null);
			item.setValorDeslocamentoMaisDeDoze(null);
			
		}else {
			item.setQtdRegressoTrezeAsDezenove(1);
			item.setValorTotalRegressoTrezeAsDezenove(item.getValorTotalDeslocamentoDasSeisAsDoze());
			item.setQtdDeslocamentoDasSeisAsDoze(null);			
			item.setValorTotalDeslocamentoDasSeisAsDoze(null);
		}
		
		item.setRetorno(true);
		}
		
	}


	public List<ItemDashDTO> getDashBoard() {
		
		List<Diaria> diarias = diariaService.findByUnidade_id();		
		List<ItemDashDTO> listDTO = new ArrayList<>();
		
		diarias.forEach(diaria ->{
			List<Double> valores = new ArrayList<>();
			Double administrativo = somaValorItemDiariaPorIdDiariaETipo(diaria.getId(), TipoDiariaEnum.ADMINISTRATIVO);
			Double seguranca = somaValorItemDiariaPorIdDiariaETipo(diaria.getId(), TipoDiariaEnum.SEGURANCA);
			
			valores.add((administrativo == null) ? 0 : administrativo);
			valores.add((seguranca == null) ? 0 : seguranca);
			
			ItemDashDTO dto = new ItemDashDTO(diaria.getMes().toString()+"/" + DateUtil.converteDateEmStringFormatoyyyy(diaria.getDataAbertura()), valores);
			listDTO.add(dto);
		});
		
		return listDTO;
	}


	public boolean existByFuncionarioDiariaDiaria_idAndAnalizado(Long idDiaria, boolean f) {
		return itemDiariaRepository.existsFuncionarioDiariaDiaria_idAndAnalizado(idDiaria, f);
	}

	public boolean existByFuncionarioDiaria_idAndAnalizado(Long idDiaria, boolean f) {
		return itemDiariaRepository.existsFuncionarioDiaria_idAndAnalizado(idDiaria, f);
	}


	public List<ItemDiaria> findByFuncionarioDiariaContaFuncionarioFuncionario_idAndDataSaidaGreaterThanEqualAndDataSaidaLessThanEqual(
			Long idFuncionario, Date dataInicial, Date dataFinal) {
		return itemDiariaRepository.findByFuncionarioDiaria_contaFuncionario_funcionario_idAndDataSaidaGreaterThanEqualAndDataSaidaLessThanEqualOrderByDataSaida(idFuncionario, dataInicial, dataFinal);
	}


	public Double somaValorDoFuncionarioPorData(Long idFuncionario, Date dInicial, Date dFinal) {
		return itemDiariaRepository.somaValorDoFuncionarioPorData(idFuncionario, dInicial, dFinal);
	}



}

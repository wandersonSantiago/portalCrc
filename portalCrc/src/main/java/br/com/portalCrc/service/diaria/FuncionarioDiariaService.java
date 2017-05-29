package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.FuncionarioDiariaRepository;
import br.com.portalCrc.repository.diaria.ValoresDiariaLocalidadeRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioDiariaService {

	@Autowired
	private FuncionarioDiariaRepository funcionarioDiariaRepository;

	@Autowired
	private ValoresDiariaLocalidadeRepository valoresDiaraRepository;

	@Transactional(readOnly = false)
	public void salvaOuAltera(FuncionarioDiaria funcionarioDiaria) {
		BigDecimal total = new BigDecimal(0);
		funcionarioDiaria.setDataCadastro(new Date());
		funcionarioDiaria.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionarioDiaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		for (int i = 0; i < funcionarioDiaria.getItens().size(); i++) {
			
			funcionarioDiaria.getItens().get(i).setFuncionarioDiaria(funcionarioDiaria);
			funcionarioDiaria.getItens().get(i).setDataCadastro(new Date());
			
		
			int count = quantidadePernoite(funcionarioDiaria.getItens().get(i));
			total = total.add(valorPernoite(count, funcionarioDiaria.getItens().get(i)));
			funcionarioDiaria.getItens().get(i).setValorDiaria(total);
		}
		funcionarioDiaria.setTotalValorDiaria(total);
		if (funcionarioDiaria.getDiaria().getStatus() == StatusDiariaEnum.ABERTO) {
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}

	}

	@Transactional(readOnly = false)
	public void altera(FuncionarioDiaria funcionarioDiaria) {
		if (funcionarioDiaria.getDiaria().getStatus() == StatusDiariaEnum.ABERTO) {
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}

	}

	public FuncionarioDiaria buscaPorId(Long id) {
		return funcionarioDiariaRepository.findOne(id);
	}

	public List<FuncionarioDiaria> listaUnidade(Long id) {
		return funcionarioDiariaRepository.findByUnidade_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
	}

	public List<FuncionarioDiaria> listaCoordenadoria(Long id) {
		return funcionarioDiariaRepository.findByUnidadeCoordenadoria_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId(),
				id);
	}

	public List<FuncionarioDiaria> lista() {
		return funcionarioDiariaRepository.findAll();
	}

	public Iterable<FuncionarioDiaria> listaSecretaria(Long id) {
		return funcionarioDiariaRepository.findByUnidadeCoordenadoriaSecretaria_id(id);
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		funcionarioDiariaRepository.delete(id);

	}

	public Iterable<ValoresDiariaLocalidade> valoresDiaria(Integer id, Long idDiaria) {
		return valoresDiaraRepository.findByIndiceUfespAndDiaria_id(id, idDiaria);
	}

	public Iterable<FuncionarioDiaria> findByUnidade_idAndDiaria_id(Long id) {
		return funcionarioDiariaRepository.findByUnidade_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
	}

	

	public BigDecimal verificaHorarioRetorno(ItemDiaria item){
		
		DateTime dataFinal = new DateTime(item.getHoraChegada());
		DateTime dataInicio = new DateTime(item.getHoraSaida());
		  
		  Hours total = Hours.hoursBetween(dataInicio, dataFinal);
		 
		  total.getHours();
		  
		return  valorDiariaPorHorario(total, item);
		
	}
	
	public BigDecimal valorDiariaPorHorario(Hours totalHoras, ItemDiaria item){
		BigDecimal totalValor = null;
		
		  if(totalHoras.getHours() > 12){
			totalValor = item.getCodigoLocalDeslocamento().getDeslocamentoMaisDeDoze();
		}
		return totalValor;
	}
			
	public Integer quantidadePernoite(ItemDiaria item) {

		DateTime   dataFinals = new DateTime(item.getDataChegada());
		DateTime  dataInicio = new DateTime(item.getDataSaida());
		
		Days d = Days.daysBetween(dataInicio, dataFinals);
		
		return d.getDays();
	}
	
	public BigDecimal valorPernoite(Integer totalPernoite, ItemDiaria item) {

		BigDecimal totalValor = null;

		if (totalPernoite > 0) {
			totalValor = item.getCodigoLocalDeslocamento().getPernoite().multiply(new BigDecimal(totalPernoite));
			
		} else if (totalPernoite == 0) {
			
			totalValor = verificaHorarioRetorno(item);
		}		
		return totalValor;
	}
}

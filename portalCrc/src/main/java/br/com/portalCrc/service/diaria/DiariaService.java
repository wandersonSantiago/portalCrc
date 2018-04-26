package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;
import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.DiariaRepository;
import br.com.portalCrc.repository.diaria.ValoresDiariaLocalidadeRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DiariaService {

	@Autowired
	private DiariaRepository diariaRepository;	
	
	@Autowired
	private ValoresDiariaLocalidadeRepository valoresDiariaLocalidadeRepository;
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(Diaria diaria) {
		if(diariasEmAberto() != null) {
			throw new MensagemException("Para Cadastrar um novo mês é necessario encerrar o anterior!");
		}
			if(verificaSeExisteMesDeDiariaNoAno(diaria) == false){
				salvarValoresDiaria(diaria, 7);
				salvarValoresDiaria(diaria, 9);
				diaria.setDataAbertura(new Date());
				diaria.setStatus(StatusDiariaEnum.ABERTO);
				diaria.setUnidadeCadastro(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
				diaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
				diariaRepository.save(diaria);
			}else{			
				throw new MensagemException("o mês de " + diaria.getMes() + " consta cadastrado no ano " + anoAtual());
			}			
		
	}
	
	@Transactional(readOnly = false)
	public void alterar(Diaria diaria){
		Diaria verificaDiaria = diariaRepository.findOne(diaria.getId());
		if(verificaDiaria.getMes() != diaria.getMes()){
			if(verificaSeExisteMesDeDiariaNoAno(diaria) == true){
				throw new MensagemException("o mês de " + diaria.getMes() + " consta cadastrado no ano " + anoAtual());
			}
		}
		verificaDiaria.setDataAlteracao(new Date());
		verificaDiaria.setUsuarioAlteracao(SessionUsuario.getInstance().getUsuario());
		verificaDiaria.setMes(diaria.getMes());
		verificaDiaria.setObservacao(diaria.getObservacao());
		diariaRepository.save(verificaDiaria);
	}
	
	@Transactional(readOnly = false)
	public void salvarValoresDiaria(Diaria diaria, Integer indice){
		
		ValoresDiariaLocalidade indice1 = new ValoresDiariaLocalidade();		
		indice1.setIndiceUfesp(indice);
		indice1.setCodigo(1);
		indice1.setDeslocamento("Distrito Federal e Manaus");
		indice1.setDiaria(diaria);
		indice1.setAlojamento(diaria.getValorUfesp().multiply(new BigDecimal(indice1.getIndiceUfesp())));
		indice1.setPernoite(indice1.getAlojamento().multiply(new BigDecimal(2)));
		indice1.setRetornoAposDezenove(indice1.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		indice1.setRetornoTrezeAsDezenove(indice1.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice1.setDeslocamentoSeisAsDoze(indice1.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice1.setDeslocamentoMaisDeDoze(indice1.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		valoresDiariaLocalidadeRepository.save(indice1);
		
		ValoresDiariaLocalidade indice2 = new ValoresDiariaLocalidade();		
		indice2.setIndiceUfesp(indice);
		indice2.setCodigo(2);
		indice2.setDeslocamento("São Paulo, Rio, Recife, Belo Horizonte, Porto Alegre, Belém, Fortaleza e Salvador");
		indice2.setDiaria(diaria);
		indice2.setAlojamento(diaria.getValorUfesp().multiply(new BigDecimal(indice2.getIndiceUfesp())).multiply(new BigDecimal(90).divide(new BigDecimal(100))));
		indice2.setPernoite(indice2.getAlojamento().multiply(new BigDecimal(2)));
		indice2.setRetornoAposDezenove(indice2.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		indice2.setRetornoTrezeAsDezenove(indice2.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice2.setDeslocamentoSeisAsDoze(indice2.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice2.setDeslocamentoMaisDeDoze(indice2.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		valoresDiariaLocalidadeRepository.save(indice2);
		
		ValoresDiariaLocalidade indice3 = new ValoresDiariaLocalidade();		
		indice3.setIndiceUfesp(indice);
		indice3.setCodigo(3);
		indice3.setDeslocamento("Demais Capitais");
		indice3.setDiaria(diaria);
		indice3.setAlojamento(diaria.getValorUfesp().multiply(new BigDecimal(indice3.getIndiceUfesp())).multiply(new BigDecimal(85).divide(new BigDecimal(100))));
		indice3.setPernoite(indice3.getAlojamento().multiply(new BigDecimal(2)));
		indice3.setRetornoAposDezenove(indice3.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		indice3.setRetornoTrezeAsDezenove(indice3.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice3.setDeslocamentoSeisAsDoze(indice3.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice3.setDeslocamentoMaisDeDoze(indice3.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		valoresDiariaLocalidadeRepository.save(indice3);
		
		ValoresDiariaLocalidade indice4 = new ValoresDiariaLocalidade();		
		indice4.setIndiceUfesp(indice);
		indice4.setCodigo(4);
		indice4.setDeslocamento("Igual ou superior a 200.000 (duzentos mil) habitantes, desde que distantes pelo menos 70 kms (setenta quilômetros) do município-sede de exercício");
		indice4.setDiaria(diaria);
		indice4.setAlojamento(diaria.getValorUfesp().multiply(new BigDecimal(indice4.getIndiceUfesp())).multiply(new BigDecimal(75).divide(new BigDecimal(100))));
		indice4.setPernoite(indice4.getAlojamento().multiply(new BigDecimal(2)));
		indice4.setRetornoAposDezenove(indice4.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		indice4.setRetornoTrezeAsDezenove(indice4.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice4.setDeslocamentoSeisAsDoze(indice4.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice4.setDeslocamentoMaisDeDoze(indice4.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		valoresDiariaLocalidadeRepository.save(indice4);
		
		ValoresDiariaLocalidade indice5 = new ValoresDiariaLocalidade();		
		indice5.setIndiceUfesp(indice);
		indice5.setCodigo(5);
		indice5.setDeslocamento("Demais deslocamento");
		indice5.setDiaria(diaria);
		indice5.setAlojamento(diaria.getValorUfesp().multiply(new BigDecimal(indice5.getIndiceUfesp())).multiply(new BigDecimal(50).divide(new BigDecimal(100))));
		indice5.setPernoite(indice5.getAlojamento().multiply(new BigDecimal(2)));
		indice5.setRetornoAposDezenove(indice5.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		indice5.setRetornoTrezeAsDezenove(indice5.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice5.setDeslocamentoSeisAsDoze(indice5.getPernoite().multiply(new BigDecimal(20)).divide(new BigDecimal(100)));
		indice5.setDeslocamentoMaisDeDoze(indice5.getPernoite().multiply(new BigDecimal(40)).divide(new BigDecimal(100)));
		valoresDiariaLocalidadeRepository.save(indice5);
	}
	
	
	
	public Diaria buscaPorId(Long id){
		return diariaRepository.findOne(id);
	}
	
	
	public Diaria diariasEmAberto(){
		return diariaRepository.findTop1ByStatusAndUnidadeCadastro_idOrderByIdDesc(StatusDiariaEnum.ABERTO, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	public Page<Diaria> lista(PageRequest pageRequest){
		return diariaRepository.findByStatus(StatusDiariaEnum.FECHADO, pageRequest);
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
	
		List<Diaria> lista = diariaRepository.findByDataAbertura(anoAtual, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
		
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

	public List<Diaria> findByUnidade_id() {
		return diariaRepository.findByUnidadeCadastro_idOrderByIdDesc(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

	public List<Diaria> findByCoordenadoriaMes(MesDiariaEnum mes) {
		return diariaRepository.findByUnidadeCadastro_idAndMesOrderByIdDesc(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), mes);
	}
	
	
}

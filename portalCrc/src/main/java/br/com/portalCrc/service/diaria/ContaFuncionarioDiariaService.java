package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.ContaFuncionarioDiaria;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.ContaFuncionarioDiariaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ContaFuncionarioDiariaService {

	

	@Autowired
	private ContaFuncionarioDiariaRepository contaFuncionarioDiariaRepository;
	private ContaFuncionarioDiaria existe;
	
	
	@Transactional(readOnly = false)
	public void salvar(ContaFuncionarioDiaria contaFuncionarioDiaria){	
		existe = contaFuncionarioDiariaRepository.findByFuncionario_idAndStatus(contaFuncionarioDiaria.getFuncionario().getId(), true);
		if(existe != null){
			existe.setStatus(false);
			contaFuncionarioDiaria.setId(null);
		}
		contaFuncionarioDiaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		contaFuncionarioDiaria.setDataCadastro(new Date());
		contaFuncionarioDiaria.setStatus(true);
		contaFuncionarioDiariaRepository.save(contaFuncionarioDiaria);

	}
	
	@Transactional(readOnly = false)
	public void altera(ContaFuncionarioDiaria contaFuncionarioDiaria){
			contaFuncionarioDiariaRepository.save(contaFuncionarioDiaria);
		
	}
	
	public ContaFuncionarioDiaria buscaPorId(Long id){
		return contaFuncionarioDiariaRepository.findOne(id);
	}
	
	public List<ContaFuncionarioDiaria> listaUnidade(){
		return contaFuncionarioDiariaRepository.findByFuncionarioUnidadeAtual_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	public List<ContaFuncionarioDiaria> listaCoordenadoria(Long id){
		return contaFuncionarioDiariaRepository.findByFuncionarioUnidadeAtualCoordenadoria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId());
	}
	public Page<ContaFuncionarioDiaria> findaAll(Pageable page){
		return contaFuncionarioDiariaRepository.findAll(page);
	}

	public Iterable<ContaFuncionarioDiaria> listaSecretaria(Long id) {		
		return contaFuncionarioDiariaRepository.findByFuncionarioUnidadeAtualCoordenadoriaSecretaria_id(id);
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		contaFuncionarioDiariaRepository.delete(id);
		
	}

	public ContaFuncionarioDiaria findByFuncionario_id(Long id) {
		ContaFuncionarioDiaria conta = contaFuncionarioDiariaRepository.findByFuncionario_idAndStatus(id, true);
		if(conta == null){
			throw new MensagemException("Este Funcionario não tem uma conta cadastrada!!!");
		}
		return conta;
	}

	public Page<ContaFuncionarioDiaria> buscar(String texto, Pageable page) {
		texto = texto.replaceAll("[./-]","");
		Page<ContaFuncionarioDiaria> list = null;
		
		if (texto.matches("[0-9]+")) {
			 list = contaFuncionarioDiariaRepository.findByFuncionarioPessoaCpfIgnoreCaseContainingAndStatus("%" + texto + "%", true, page);			
		} else {
			 list =  contaFuncionarioDiariaRepository.findByFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndStatus(texto, true, page);			
		}
		if(list == null || list.getNumberOfElements() < 1){
			throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
		}
		return list;
	}
	
}

	


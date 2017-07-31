package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ContaFuncionarioDiaria> lista(){
		return contaFuncionarioDiariaRepository.findAll();
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

	public List<ContaFuncionarioDiaria> buscar(String texto) {
		texto = texto.replaceAll("[./-]","");
		if (texto.matches("[0-9]+")) {
			List<ContaFuncionarioDiaria> list = contaFuncionarioDiariaRepository.findByFuncionarioPessoaCpfAndStatus("%" + texto + "%", true);
			if(list.isEmpty() || list == null){
				throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
			}
			return list;
		} else {
			List<ContaFuncionarioDiaria> list =  contaFuncionarioDiariaRepository.findByFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndStatus(texto, true);
			if(list.isEmpty() || list == null){
				throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
			}
			return list;
		}
	}
	
}

	


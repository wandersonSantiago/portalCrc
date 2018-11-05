package br.com.portalCrc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.FuncionarioCargo;
import br.com.portalCrc.entity.FuncionarioUnidade;
import br.com.portalCrc.enums.StatusFuncionario;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.FuncionarioCargoRepository;
import br.com.portalCrc.repository.FuncionarioRepository;
import br.com.portalCrc.repository.FuncionarioUnidadeRepository;
import br.com.portalCrc.service.diaria.MensagemException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioService {

	@Autowired 
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioCargoRepository funcionarioCargoRepository;
	
	@Autowired
	private FuncionarioUnidadeRepository funcionarioUnidadeRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Funcionario funcionario){
		salvaHistoricoDeCargo(funcionario);
		if(funcionario.getId() == null) {
			salvaHistoricoDaUnidade(funcionario);
		}		
		funcionario.setUnidadeAtual(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionario.setDataCadastro(new Date());
		funcionarioRepository.save(funcionario);
	}
	
		
	public Funcionario buscaPorId(Long id){
		return funcionarioRepository.findOne(id);
	}
	
	@Transactional(readOnly= false)
	public void salvaHistoricoDeCargo(Funcionario funcionario){
		FuncionarioCargo cargos =  new FuncionarioCargo();
		cargos.setCargo(funcionario.getCargoAtual());
		cargos.setFuncionario(funcionario);
		cargos.setDataCadastro(new Date());
		funcionarioCargoRepository.save(cargos);
	}
	
	@Transactional(readOnly = false)
	public void salvaHistoricoDaUnidade(Funcionario funcionario){
		FuncionarioUnidade unidade = new FuncionarioUnidade();
		unidade.setAtivo(true);
		unidade.setFuncionario(funcionario);
		unidade.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		unidade.setStatus(StatusFuncionario.LOTADO);
		unidade.setDataCadastro(new Date());
		unidade.setCargo(funcionario.getCargoAtual());
		funcionarioUnidadeRepository.save(unidade);
	}

	public Iterable<Funcionario> listaPorUnidade() {		
		return funcionarioRepository.findByUnidadeAtual_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	public Page<Funcionario> buscar(String texto, Pageable page) {
			texto = texto.replaceAll("[./-]","");
			Page<Funcionario> list;
			if (texto.matches("[0-9]+")) {
				 list = funcionarioRepository.findByPessoa_cpfContaining("%" + texto + "%", page);
			} else {
				 list =  funcionarioRepository.findByPessoa_nomeCompletoIgnoreCaseContaining(texto, page);
			}
			if(list == null || list.getNumberOfElements() < 1){
				throw new MensagemException("Busca não encontrada,  para este funcionario! " + texto);
			}
			return list;
	}

	public Page<Funcionario> buscarNaUnidade(String texto, Pageable page) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
			texto = texto.replaceAll("[./-]","");
			if (texto.matches("[0-9]+")) {
				Page<Funcionario> list = funcionarioRepository.findByUnidadeAtual_idAndPessoa_cpfContaining(idUnidade, "%" + texto + "%", page);
				if(list == null || list.getNumberOfElements() < 1){
					throw new MensagemException("Busca não encontrada,  para este funcionario! " + texto);
				}
				return list;
			} else {
				Page<Funcionario> list =  funcionarioRepository.findByUnidadeAtual_idAndPessoaNomeCompletoIgnoreCaseContaining(idUnidade,texto, page);
				if(list == null || list.getNumberOfElements() < 1){
					throw new MensagemException("Busca não encontrada,  para este funcionario! " + texto);
				}
				return list;
			}
	}

	public Funcionario verificaCpf(String cpf) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
		
		Funcionario funcionario = funcionarioRepository.findByPessoa_cpfAndUnidadeAtual_id(cpf , idUnidade);
		if(funcionario == null){			
			throw new MensagemException("Liberado para cadastro!! " + cpf);
		}		
		return funcionario;
	}

	public Page<Funcionario> findAllUnidade(PageRequest pageRequest) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
		return funcionarioRepository.findAllByUnidadeAtual_id(idUnidade, pageRequest);
	}
	
	public List<Funcionario> findAllUnidadeId() {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
		if(idUnidade == null) {
			throw new MensagemException("Unidade não pode ser nula");
		}
		return funcionarioRepository.findAllByUnidadeAtual_idOrderByPessoaNomeCompleto(idUnidade);
	}
	public Page<Funcionario> findAll(PageRequest pageRequest) {
		return funcionarioRepository.findAll(pageRequest);
	}
}

package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import scala.annotation.meta.setter;

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
		salvaHistoricoDaUnidade(funcionario);
		funcionario.setUnidadeAtual(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionario.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		funcionario.setDataCadastro(new Date());
		funcionarioRepository.save(funcionario);
	}
	
	public Collection<Funcionario> lista(){
		return funcionarioRepository.findAll();
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
		cargos.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		funcionarioCargoRepository.save(cargos);
	}
	
	@Transactional(readOnly = false)
	public void salvaHistoricoDaUnidade(Funcionario funcionario){
		FuncionarioUnidade unidades = new FuncionarioUnidade();
		unidades.setAtivo(true);
		unidades.setFuncionario(funcionario);
		unidades.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		unidades.setStatus(StatusFuncionario.LOTADO);
		unidades.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		unidades.setDataCadastro(new Date());
		funcionarioUnidadeRepository.save(unidades);
	}

	public Iterable<Funcionario> listaPorUnidade() {		
		return funcionarioRepository.findByUnidadeAtual_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	public List<Funcionario> buscar(String texto) {
		Long unidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
			texto = texto.replaceAll("[./-]","");
			if (texto.matches("[0-9]+")) {
				List<Funcionario> list = funcionarioRepository.findByUnidadeAtual_idAndPessoaCpf(unidade, "%" + texto + "%");
				if(list.isEmpty() || list == null){
					throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
				}
				return list;
			} else {
				List<Funcionario> list =  funcionarioRepository.findByUnidadeAtual_idAndPessoaNomeCompletoIgnoreCaseContaining(unidade,texto);
				if(list.isEmpty() || list == null){
					throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
				}
				return list;
			}
	}
}

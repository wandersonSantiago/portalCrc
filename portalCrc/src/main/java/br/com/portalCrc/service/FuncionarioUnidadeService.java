package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.FuncionarioUnidade;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.FuncionarioRepository;
import br.com.portalCrc.repository.FuncionarioUnidadeRepository;
import br.com.portalCrc.repository.UnidadeRepository;
import br.com.portalCrc.repository.UsuarioRepository;
import br.com.portalCrc.service.diaria.MensagemException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioUnidadeService {

	
	@Autowired 
	private FuncionarioUnidadeRepository funcionarioUnidadeRepository;	
	@Autowired 
	private FuncionarioRepository funcionarioRepository;	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	
	
	public Collection<FuncionarioUnidade> lista(){
		return funcionarioUnidadeRepository.findAll();
	}
	
	public FuncionarioUnidade buscaPorId(Long id){
		return funcionarioUnidadeRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void salvarEditar(FuncionarioUnidade funcionarioUnidade){
		salvaHistoricoFuncionario(funcionarioUnidade.getFuncionario(), funcionarioUnidade);
		criarUsuario(funcionarioUnidade.getFuncionario());
		funcionarioUnidade.setDataCadastro(new Date());
		funcionarioUnidade.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionarioUnidade.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		funcionarioUnidadeRepository.save(funcionarioUnidade);
	}
	
	@Transactional(readOnly = false)
	public void salvaHistoricoFuncionario(Funcionario funcionario, FuncionarioUnidade funcionarioUnidade){
		funcionario.setCargoAtual(funcionarioUnidade.getCargo());
		funcionario.setUnidadeAtual(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionario.setFuncaoAtual(funcionarioUnidade.getFuncao());
		funcionario.setSetorAtual(funcionarioUnidade.getSetor());
		funcionarioRepository.save(funcionario);
		
	}
	@Transactional(readOnly = false)
	public void criarUsuario(Funcionario funcionario){		
	
			Usuario usuarioVerificado = usuarioRepository.findByLogin(funcionario.getPessoa().getCpf());
	
		if( usuarioVerificado != null){
			usuarioVerificado.setFuncionario(funcionario);
			usuarioRepository.save(usuarioVerificado);
		}else{
			Usuario usuario = new Usuario();
			usuario.setDataCadastro(new Date());
			usuario.setFuncionario(funcionario);
			usuario.setLogin(funcionario.getPessoa().getCpf());		
			String hash = new BCryptPasswordEncoder().encode(funcionario.getPessoa().getCpf());
			usuario.setSenha(hash);
			usuarioRepository.save(usuario);
		}		
		
		
	}

	public FuncionarioUnidade findByFuncionario_idTop1Desc(Long id) {		
		return funcionarioUnidadeRepository.findTop1ByFuncionario_idOrderByIdDesc(id);
	}

	public List<FuncionarioUnidade> buscar(String texto) {
		Long unidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
			texto = texto.replaceAll("[./-]","");
			if (texto.matches("[0-9]+")) {
				List<FuncionarioUnidade> list = funcionarioUnidadeRepository.findDistinctFuncionario_cpfByUnidade_idAndFuncionarioPessoaCpf(unidade, "%" + texto + "%");
				if(list.isEmpty() || list == null){
					throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
				}
				return list;
			} else {
				List<FuncionarioUnidade> list =  funcionarioUnidadeRepository.findDistinctFuncionario_cpfByUnidade_idAndFuncionarioPessoaNomeCompletoIgnoreCaseContaining(unidade,texto);
				if(list.isEmpty() || list == null){
					throw new MensagemException("Busca não encontrada, verifique se ja existe conta aberta para este funcioonario! " + texto);
				}
				return list;
			}
	}

	@Transactional(readOnly = false)
	public void alterarUnidade(Long idUnidade) {
		Funcionario funcionario = SessionUsuario.getInstance().getUsuario().getFuncionario();
		Unidade unidade = unidadeRepository.findOne(idUnidade);
		funcionario.setUnidadeAtual(unidade);
		
		funcionarioRepository.save(funcionario);
	}

	
}

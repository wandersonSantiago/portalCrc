package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.FuncionarioUnidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.StatusFuncionario;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.FuncionarioRepository;
import br.com.portalCrc.repository.FuncionarioUnidadeRepository;
import br.com.portalCrc.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioUnidadeService {

	
	@Autowired 
	private FuncionarioUnidadeRepository funcionarioUnidadeRepository;	
	@Autowired 
	private FuncionarioRepository funcionarioRepository;	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	
	
	
	public Collection<FuncionarioUnidade> lista(){
		return funcionarioUnidadeRepository.findAll();
	}
	
	public FuncionarioUnidade buscaPorId(Long id){
		return funcionarioUnidadeRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void salvarEditar(FuncionarioUnidade funcionarioUnidade){
		salvaHistoricoFuncionario(funcionarioUnidade);
		criarUsuario(funcionarioUnidade.getFuncionario());
		funcionarioUnidade.setDataCadastro(new Date());
		funcionarioUnidade.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionarioUnidade.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		funcionarioUnidadeRepository.save(funcionarioUnidade);
	}
	
	@Transactional(readOnly = false)
	public void salvaHistoricoFuncionario(FuncionarioUnidade funcionarioUnidade){
		funcionarioUnidade.getFuncionario().setCargoAtual(funcionarioUnidade.getCargo());
		funcionarioUnidade.getFuncionario().setUnidadeAtual(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionarioUnidade.getFuncionario().setFuncaoAtual(funcionarioUnidade.getFuncao());
		funcionarioUnidade.getFuncionario().setSetorAtual(funcionarioUnidade.getSetor());
		funcionarioRepository.save(funcionarioUnidade.getFuncionario());
		
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

	
}

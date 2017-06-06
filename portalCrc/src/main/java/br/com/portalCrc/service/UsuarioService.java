package br.com.portalCrc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.repository.UsuarioRepository;
import br.com.portalCrc.service.diaria.MensagemException;




@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class UsuarioService {


	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario buscarUsuarioPorNome(String nomeUsuario)
	{
		 return null;
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Usuario usuario)
	{
		String hash = null;
		if(usuario.getSenha() != null){
			hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(hash);
		}else{
			Usuario user = usuarioRepository.findOne(usuario.getId());	
			hash = user.getSenha();
		}		
		usuario.setSenha(hash);		
		usuarioRepository.save(usuario);
	}
	
	public void alterarSenha(Long idUsuario, String senhaValidacao, String novaSenha) {
		Usuario user = usuarioRepository.findOne(idUsuario);	
		String hash = new BCryptPasswordEncoder().encode(senhaValidacao);
		
		if(user.getSenha() == hash){
			String novaHash = new BCryptPasswordEncoder().encode(novaSenha);
			user.setSenha(novaHash);
			usuarioRepository.save(user);
		}else{
			throw new MensagemException("Senha de verificação inválida!!!");			
		}
	}
	
	public Usuario buscarUsuarioPorId(Long id){
		return usuarioRepository.findOne(id);
	}
	
	public List<Usuario> buscarTodos(){
			
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarPorLogin(String login)
	{
		return usuarioRepository.findByLogin(login);
	}
	
	public boolean existeLoginCadastrado(String login)
	{
		return usuarioRepository.existeLogin(login);
	}



    public void save(String path, Usuario  user) {

    user.setCaminhoFoto(path);
    	usuarioRepository.save(user);
    }

   

    

    public String createPath() {

        LocalDate localDate = LocalDate.now();

        String mm = String.valueOf(localDate.getMonthValue());
        String yyyy = String.valueOf(localDate.getYear());

        return "C:\\uploads" + "\\" + yyyy + "\\" + mm;
    }

	

}

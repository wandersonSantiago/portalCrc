package br.com.portalCrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.UsuarioRepository;
import br.com.portalCrc.service.diaria.MensagemException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ImagemService imagemService;
	
	public Usuario buscarUsuarioPorNome(String nomeUsuario) {
		return null;
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(Usuario usuario) {
		String hash = null;
		if (usuario.getId() == null) {
			hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(hash);
		} else {
			Usuario user = usuarioRepository.findOne(usuario.getId());
			hash = user.getSenha();
		}
		usuario.setSenha(hash);
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void alterarSenha(Long idUsuario, String senhaValidacao, String novaSenha) {
		Usuario user = usuarioRepository.findOne(idUsuario);
		// String hash = new BCryptPasswordEncoder().encode(senhaValidacao);

		// if(user.getSenha() == hash){
		String novaHash = new BCryptPasswordEncoder().encode(novaSenha);
		user.setSenha(novaHash);
		usuarioRepository.save(user);
		/*
		 * }else{ throw new
		 * MensagemException("Senha de verificação inválida!!!"); }
		 */
	}

	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioRepository.findOne(id);
	}

	public List<Usuario> buscarTodos() {

		return usuarioRepository.findAll();
	}

	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public boolean existeLoginCadastrado(String login) {
		return usuarioRepository.existeLogin(login);
	}

	@Transactional(readOnly = false)
	public void save(String path, Usuario user) {

		user.setCaminhoFoto(path);
		usuarioRepository.save(user);
	}

	public List<Usuario> buscar(String texto) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
		texto = texto.replaceAll("[./-]", "");
		if (texto.matches("[0-9]+")) {
			List<Usuario> list = usuarioRepository
					.findByFuncionarioPessoaCpfAndFuncionario_unidadeAtual_id("%" + texto + "%", idUnidade);
			if (list.isEmpty() || list == null) {
				throw new MensagemException(
						"Busca não encontrada, verifique se este funcioonario esta cadastrado nesta Unidade! " + texto);
			}
			return list;
		} else {
			List<Usuario> list = usuarioRepository
					.findByFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndFuncionario_unidadeAtual_id(texto,
							idUnidade);
			if (list.isEmpty() || list == null) {
				throw new MensagemException(
						"Busca não encontrada, verifique se este funcioonario esta cadastrado nesta Unidade! " + texto);
			}
			return list;
		}
	}

	public String caminhoFoto(MultipartFile file) {
		
		Usuario  user = SessionUsuario.getInstance().getUsuario();
	      
	       String login = String.valueOf(user.getLogin());
	                          
	      	       
	       String caminho = "C:/fotos/"+login+"/";
	       
	       return caminho;

	}

	/*public String createPath() {

		Usuario user = SessionUsuario.getInstance().getUsuario();

		String login = String.valueOf(user.getLogin());

		return "C:/fotos/" + login;
	}

	public InputStream getFoto(@PathVariable String caminho) throws FileNotFoundException {

		InputStream in = null;

		try {
			in = buscarFoto(caminho);
		} catch (MensagemException e) {
			in = new FileInputStream("src/main/resources/static/public/img/avatar_2x.png");

		}
		return in;
	}

	private InputStream buscarFoto(String caminho) {
		if (!caminho.endsWith("/")) {
			caminho = caminho + "/";
		}
		InputStream in = null;
		try {
			in = new FileInputStream(caminho + ".jpg");
		} catch (FileNotFoundException e) {
			throw new MensagemException("Foto não encontrada: " + e.getMessage());
		}
		return in;
	}
*/
}

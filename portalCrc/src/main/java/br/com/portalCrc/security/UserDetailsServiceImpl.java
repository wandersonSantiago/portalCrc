package br.com.portalCrc.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Permissao;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.service.UsuarioService;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioService.buscarPorLogin(username);
		if (usuario != null) {
			UserDetailsImpl user = new UserDetailsImpl();

			user.setUsername(usuario.getLogin());
			user.setPassword(usuario.getSenha());

			for(Permissao perl : usuario.getPermissoes())
			{
				user.addAuthority("ROLE_?" + perl.getDescricao());
				
			}
			
			return user;
		}
		throw new UsernameNotFoundException("Usuario não encontrado");
	}

}

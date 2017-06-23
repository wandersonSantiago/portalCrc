package br.com.portalCrc.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.service.UsuarioService;


@Component
public class LoginHandler implements ApplicationListener<AuthenticationSuccessEvent>{

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HttpSession session;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {

		String login = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		
		Usuario usuario = usuarioService.buscarPorLogin(login);
		if(usuario != null)
		{
			session.setAttribute("usuario", usuario);
		}
	}

}

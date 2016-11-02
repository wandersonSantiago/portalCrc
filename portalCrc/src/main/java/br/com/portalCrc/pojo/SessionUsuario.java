package br.com.portalCrc.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.portalCrc.entity.Usuario;


public class SessionUsuario {

	public static SessionUsuario instance;

	private SessionUsuario() {

	}
   public synchronized Usuario getUsuario() {
		
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		return usuario;
	}

	public static SessionUsuario getInstance() {

		if (instance == null) {
			instance = new SessionUsuario();

		}

		return instance;
	}
}

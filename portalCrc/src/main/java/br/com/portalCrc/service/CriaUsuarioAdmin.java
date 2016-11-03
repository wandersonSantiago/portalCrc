package br.com.portalCrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.portalCrc.entity.Usuario;


@Component
public class CriaUsuarioAdmin implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UsuarioService usuarioService;
	
	
	private void criarUsuario()
	{
		if(usuarioService.existeLoginCadastrado("root") == false)
		{
			System.out.println("Usuário root não encontrado.");
			System.out.println("Criando usuário root.");
			usuarioService.salvarOuEditar(criarObjetoUsuario());
		    System.out.println("Usuário root criado.");
		}else
		{
			System.out.println("Usuário root [OK]");
		}
			
	}
    private Usuario criarObjetoUsuario()
    {
    	Usuario usuario = new Usuario();
		
		usuario.setLogin("root");
		usuario.setEmail("root@suporte.com.br");
	    usuario.setSenha("root");
	    usuario.setNome("Usuario root");
	    return usuario;
    }
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		criarUsuario();
		
	}
}

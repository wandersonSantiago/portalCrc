package br.com.portalCrc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.repository.PermissaoRepository;


@Component
public class CriaUsuarioAdmin implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	
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
    	/*Permissao permissao = new Permissao();
    	permissao.setDescricao("ADMIN");
    	permissao.setModulo(ModuloPermissaoEnum.ADMIN);
    	permissaoRepository.save(permissao);
    	
    	List<Permissao> list = new ArrayList<>(); 
    	
    	for(int i = 0 ; i < list.size() ; i++){
    		Permissao permission = permissaoRepository.findOne(1L);
    		list.add(permission);
    	}*/
    	Usuario usuario = new Usuario();
		
		usuario.setLogin("root");
	    usuario.setSenha("root");
	  //  usuario.setPermissoes(list);
	    return usuario;
    }
    
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		criarUsuario();
		
	}
}

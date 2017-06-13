package br.com.portalCrc.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.StatusUsuarioEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.service.UsuarioService;
import br.com.portalCrc.service.diaria.MensagemException;




@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;

    private String path;
    private String name;
    private String type;
    private Long size;

    
	@RequestMapping(value="/usuario")
	@ResponseBody
	public Principal user(Principal user, HttpSession session) {				
		return user;
	}
	
    @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Usuario>> buscarUsuarios() {	
	  Iterable<Usuario> usuario = usuarioService.buscarTodos();
	  return new ResponseEntity<Iterable<Usuario>>(usuario, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/salva")
	 public ResponseEntity<Usuario> salva(@RequestBody Usuario usuario,UriComponentsBuilder ucBuilder){
		 usuarioService.salvarOuEditar(usuario);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/usuario/salva/{id}").buildAndExpand(usuario.getId()).toUri());
		 return new ResponseEntity<Usuario>(headers, HttpStatus.CREATED);
	 }

	 @PutMapping(value="/alterar")
	 public ResponseEntity<Usuario> alterarUsuario(@RequestBody Usuario usuario,UriComponentsBuilder ucBuilder){		 
		 usuarioService.salvarOuEditar(usuario);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/usuario/altera/{id}").buildAndExpand(usuario.getId()).toUri());
		 return new ResponseEntity<Usuario>(headers, HttpStatus.CREATED);
	 }
	 
	 @PutMapping(value="/{idUsuario}/senha/{senha}/nova-senha/{novaSenha}")
	 public ResponseEntity<Usuario> alterarSenha(@PathVariable Long idUsuario, @PathVariable String senha , @PathVariable String novaSenha){		 
		 usuarioService.alterarSenha(idUsuario, senha , novaSenha);
		 HttpHeaders headers =new HttpHeaders();
		 return new ResponseEntity<Usuario>(headers, HttpStatus.CREATED);
	 }

	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
			return new ResponseEntity<Usuario>(usuarioService.buscarUsuarioPorId(id), HttpStatus.OK);
		}
	 @RequestMapping(value = "/existeLogin/{login}", method = RequestMethod.GET)
	 public ResponseEntity<Usuario> verificarSeExisteLogin(@PathVariable String login) {
			return new ResponseEntity(usuarioService.existeLoginCadastrado(login), HttpStatus.OK);
		}
	 
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/status")
		public ResponseEntity<Iterable<StatusUsuarioEnum>> status() {
			Iterable<StatusUsuarioEnum> statusUsuario = Arrays.asList(StatusUsuarioEnum.values());
			return new ResponseEntity<Iterable<StatusUsuarioEnum>>(statusUsuario, HttpStatus.OK);
		}
	 
   @RequestMapping("/usuarios")
		public ResponseEntity<?> user(SessionUsuario user, HttpSession session) {
	
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
   
   @GetMapping(value = "/buscar")
	public ResponseEntity<?> buscar(@RequestParam("q")String texto) {
	 return new ResponseEntity<List<Usuario>>(usuarioService.buscar(texto), HttpStatus.OK);
	}
   
   @PostMapping(value = "/foto")
	public ResponseEntity<?> recebeImagem( @RequestPart("file") MultipartFile file,  @RequestPart("usuario") Usuario usuario) {

       path = "";
       name = file.getOriginalFilename();
       type = file.getContentType();
       size = file.getSize();

       try {
           byte[] bytes = file.getBytes();

           path = usuarioService.createPath();

           File dir = new File(path);

           if (!dir.exists()) {
               dir.mkdirs();
           }

           File serverFile = new File(dir.getAbsolutePath() + "\\" + name);

           if (!serverFile.exists()) {

               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

               stream.write(bytes);

               stream.close();

               usuarioService.save(path, usuario);

           } else {

        		throw new MensagemException("Não foi possivel salvar a foto");
        		
           }

       } catch (Exception e) {
    	   e.printStackTrace();
    	   throw new MensagemException("Não foi possivel salvar a foto" + e.getMessage());
       }
       return new ResponseEntity<>(HttpStatus.CREATED);


      
   }

}

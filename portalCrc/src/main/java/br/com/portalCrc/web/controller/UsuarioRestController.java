package br.com.portalCrc.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import br.com.portalCrc.service.ImagemService;
import br.com.portalCrc.service.UsuarioService;
import br.com.portalCrc.service.diaria.MensagemException;




@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ImagemService imagemService;


    
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
	 public ResponseEntity<Object> verificarSeExisteLogin(@PathVariable String login) {
			return new ResponseEntity<Object>(usuarioService.existeLoginCadastrado(login), HttpStatus.OK);
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

	   String name = imagemService.userName(file);
	   String caminho = usuarioService.caminhoFoto(file);
       
       try {  	              
           imagemService.createPathAndSaveFile(caminho,  name, file);         
           usuarioService.save(caminho + name, usuario);

       } catch (Exception e) {
    	   e.printStackTrace();
    	   throw new MensagemException("Não foi possivel salvar a foto" + e.getMessage());
       }
       return new ResponseEntity<>(HttpStatus.CREATED);      
   }
   
   
   	@ResponseBody
	@RequestMapping(value="/{id}/foto", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> getFotoPreso(@PathVariable Long id) throws FileNotFoundException{
   		Usuario user = usuarioService.buscarUsuarioPorId(id);
   		
		InputStream in = imagemService.getFoto(user.getCaminhoFoto());
		return ResponseEntity.ok()
				.contentType(org.springframework.http.MediaType.IMAGE_JPEG)
				.body(new InputStreamResource(in));
	}



   	
}

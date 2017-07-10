package br.com.portalCrc.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import br.com.portalCrc.service.diaria.MensagemException;

@Service
public class ImagemService {
	
	//Cria um nome para o arquivo
	public String userName(MultipartFile file){	       
	       String userName = "user-name"; 
	       return userName;
	}
	
	//Cria um diretório para o arquivo
	  public void createPathAndSaveFile(String path,  String name, MultipartFile file) throws IOException {	  
          
		  byte[] bytes = file.getBytes();
		  
          File dir = new File(path);

          if (!dir.exists()) {
              dir.mkdirs();
          }

          File serverFile = new File(dir.getAbsolutePath() + "/" + name);
          BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

          if (!serverFile.exists()) {
              stream.write(bytes);
              stream.close();
             
          } else {
        	  serverFile.delete();
        	  stream.write(bytes);
              stream.close();
       		
          }
	    }

		
	 public InputStream getFoto(@PathVariable String  caminho) throws FileNotFoundException {
						
			InputStream in = null;
			
			try {			
				in =  buscarFoto(caminho);			
			} catch (MensagemException e) {			
				in =  new FileInputStream("src/main/resources/static/public/img/avatar_2x.png");				
				
			}
			return in;
		}

	   private InputStream buscarFoto( String caminho) {
			if (!caminho.endsWith("/")) {
				caminho = caminho + "/";
			}
			InputStream in = null;
			try {
				in = new FileInputStream(caminho);
			} catch (FileNotFoundException e) {
				throw new MensagemException("Foto não encontrada: " + e.getMessage());
			}
			return in;
		}
		

}

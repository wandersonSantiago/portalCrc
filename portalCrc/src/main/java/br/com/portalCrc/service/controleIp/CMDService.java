package br.com.portalCrc.service.controleIp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.portalCrc.RestClient;
import br.com.portalCrc.entity.controleIp.Comando;
import br.com.portalCrc.util.URLUtils;

@Service
public class CMDService {

	@Autowired
	private  RestClient restClient;
	
	
	
	public Comando fullexecuter(Comando comando) {		
		ResponseEntity<Comando> u = restClient.postForEntity(URLUtils.getServico(comando.getIp(), "executar"), comando, Comando.class);
	 return u.getBody();
	}



	public Comando host(Comando comando) {
		ResponseEntity<Comando> u = restClient.postForEntity(URLUtils.getServico(comando.getIp(), "host"), comando, Comando.class);
		return u.getBody();
	}
}

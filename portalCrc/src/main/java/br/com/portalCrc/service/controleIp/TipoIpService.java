package br.com.portalCrc.service.controleIp;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portalCrc.entity.controleIp.TipoIp;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.TipoIpRepositorio;

@Service
@Transactional
public class TipoIpService {
	
	@Autowired
	private TipoIpRepositorio tipoIpRepositorio;
	
	@Transactional
	public void salvaOuAltera(TipoIp tipo){
		tipo.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
		tipo.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario().getId());
		tipoIpRepositorio.save(tipo);
				
	}
	
	
	public Collection<TipoIp> lista(){
		return tipoIpRepositorio.findByUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	
	public TipoIp buscaPorId(Long id){
		return tipoIpRepositorio.findOne(id);
		
	}
}

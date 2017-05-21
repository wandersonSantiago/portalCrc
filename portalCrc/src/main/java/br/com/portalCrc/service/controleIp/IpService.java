package br.com.portalCrc.service.controleIp;

import java.text.NumberFormat;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.entity.controleIp.TipoIp;
import br.com.portalCrc.enums.controleIp.StatusIp;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.IpRepositorio;

@Service
@Transactional(readOnly = true , propagation = Propagation.REQUIRED)
public class IpService {

	@Autowired
	private IpRepositorio ipRepositorio;	
	
	@Transactional(readOnly = false)
	public void salvar(Ip ip){
		
		String range = ip.getNumero();
		TipoIp tipo = ip.getTipo();
		
		for(int i = 1; i <= 255 ; i++ ){
			
			String numero = NumberFormat.getInstance().format(i);			
			ip.setNumero(range + numero);
			ip.setTipo(tipo);
			ip.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
			ip.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			ip.setStatus(StatusIp.INATIVO);
			ipRepositorio.save(ip);
			
			ip = new Ip();
		}
				
	}
	
	@Transactional(readOnly = false)
	public void altera(Ip ip){
		ipRepositorio.save(ip);
	}
	
	public Collection<Ip> lista(){
		return ipRepositorio.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}
	
	public Ip buscaPorId(Long id){
		return ipRepositorio.findOne(id);
	}

	public Iterable<Ip> listaIp(StatusIp status) {
		return ipRepositorio.findByStatusAndUnidade_idOrderByIdAsc(status, SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

}

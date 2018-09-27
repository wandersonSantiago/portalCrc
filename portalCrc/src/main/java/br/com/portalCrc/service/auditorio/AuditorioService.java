package br.com.portalCrc.service.auditorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.auditorio.Auditorio;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.auditorio.AuditorioRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AuditorioService {
	
	@Autowired		
	private AuditorioRepository auditorioRepository;
		
		@Transactional(readOnly = false)
		public void salvarEditar(Auditorio auditorio){
			auditorio.setDataCadastro(new Date());
			auditorio.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			auditorio.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
			auditorioRepository.save(auditorio);
		}
		
		public Collection<Auditorio> lista(){
			return auditorioRepository.findAll();
		}
		
		public Auditorio buscaPorId(Long id){
			return auditorioRepository.findOne(id);
		}

	

}

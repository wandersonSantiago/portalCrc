package br.com.portalCrc.service.auditorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.auditorio.Reserva;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.auditorio.ReservaRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
		
		@Transactional(readOnly = false)
		public void salvarEditar(Reserva reserva) {
			//reserva.setDataReserva(new Date());
			reserva.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			//reserva.setUnidadeCadastro(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
			reservaRepository.save(reserva);
		}
		
		public Collection<Reserva> lista(){
			return reservaRepository.findAll();
			
		}
		
		public Reserva buscaPorId(Long id){
			return reservaRepository.findOne(id);
		}
}		


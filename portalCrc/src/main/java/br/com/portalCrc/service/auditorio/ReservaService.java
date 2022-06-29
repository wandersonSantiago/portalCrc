package br.com.portalCrc.service.auditorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.auditorio.Reserva;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;
import br.com.portalCrc.enums.controleIp.StatusIp;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.auditorio.ReservaRepository;
import br.com.portalCrc.service.diaria.MensagemException;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
		@Transactional(readOnly = false)
		public void insert(Reserva reserva) {
			
				int existe =  reservaRepository.countSeExisteReservaNaMesmaData(reserva.getAuditorio().getId(), reserva.getDataReserva(), reserva.getHoraReservaInicio(), reserva.getHoraReservaTermino());
			
				if(existe > 0){
			          throw new MensagemException("Ja consta agendamento na data e hora mencionada. Escolha outra data ou Horario que não conflite com horario ja reservado!");
				}			
			reserva.setDataCadastro(new Date());
			reserva.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
							
			reservaRepository.save(reserva);
		}
		
		@Transactional(readOnly = false)
		public void update(Reserva reserva) {			
			
				Reserva existe =  reservaRepository.verificaSeExisteReservaNaMesmaData(	reserva.getAuditorio().getId(), 
						reserva.getDataReserva(), reserva.getHoraReservaInicio(), reserva.getHoraReservaTermino());	   
				
				if(!reserva.equals(existe)) {
			          throw new MensagemException("Ja consta agendamento na data e hora mencionada. Escolha outra data ou Horario que não conflite com horario ja reservado!");
				}
			reservaRepository.save(reserva);
		}
		
		public Collection<Reserva> lista(){
			return reservaRepository.findAll();
			
		}
		
		public Reserva buscaPorId(Long id){
			return reservaRepository.findOne(id);
		}
		
		public Page<Reserva> buscar(String texto, Pageable page) {
			texto = texto.replaceAll("[./-]","");
			Page<Reserva> list;
			if (texto.matches("[0-9]+")) {
				 list = reservaRepository.findById(new Long(texto), page);
			} else {
				 list =  reservaRepository.findByEventoIgnoreCaseContaining(texto, page);
			}
			if(list == null || list.getNumberOfElements() < 1){
				throw new MensagemException("Busca não encontrada,  para este funcionario! " + texto);
			}
			return list;
	}
		
		public Page<Reserva> findAll(PageRequest pageRequest) {
			return reservaRepository.findAll(pageRequest);
		}	
		
		
}		


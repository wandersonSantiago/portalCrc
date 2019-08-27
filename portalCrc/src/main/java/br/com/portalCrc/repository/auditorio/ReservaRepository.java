package br.com.portalCrc.repository.auditorio;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.portalCrc.entity.auditorio.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	Page<Reserva> findByEventoIgnoreCaseContaining(String texto, Pageable page);

	Page<Reserva> findById(Long id, Pageable page);

	@Query("FROM Reserva r where auditorio.id = ?1 and dataReserva = ?2 and horaReservaInicio >= ?3 and horaReservaTermino <= ?4")
	Reserva verificaSeExisteReservaNaMesmaData(Long idAuditorio, Date dataReserva, Date horaReservaInicio, Date horaReservaTermino);

	@Query("SELECT COUNT(*) FROM Reserva r where auditorio.id = ?1 and dataReserva = ?2 and horaReservaInicio >= ?3 and horaReservaTermino <= ?4")
	int countSeExisteReservaNaMesmaData(long id, Date dataReserva, Date horaReservaInicio, Date horaReservaTermino);


	

}

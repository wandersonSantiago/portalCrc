package br.com.portalCrc.repository.auditorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.com.portalCrc.entity.auditorio.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	Page<Reserva> findByEventoIgnoreCaseContaining(String texto, Pageable page);

	Page<Reserva> findById(Long id, Pageable page);

}

package br.com.portalCrc.repository.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.infra.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

}

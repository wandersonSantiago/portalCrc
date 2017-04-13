package br.com.portalCrc.repository.diaria;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;

public interface ValoresDiariaLocalidadeRepository extends JpaRepository<ValoresDiariaLocalidade, Long> {

	Iterable<ValoresDiariaLocalidade> findByIndiceUfesp(Integer id);

}

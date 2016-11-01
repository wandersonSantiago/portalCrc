package br.com.portalCrc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="secretaria")
public class Secretaria extends AbstractPersistable<Long>{

	@OneToOne
	@JoinColumn(name="id_dados_unidade")
	private DadosUnidade dadosUnidade;
		
	
	
}

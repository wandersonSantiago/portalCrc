package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "ufesp_id_seq", sequenceName = "ufesp_id_seq", schema="diaria", initialValue = 1, allocationSize = 1)
@Table(name="ufesp", schema="diaria")
public class Ufesp {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ufesp_id_seq")
	private Long id;
	
	private BigDecimal valor;
	
	private Date dataCadastro;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}

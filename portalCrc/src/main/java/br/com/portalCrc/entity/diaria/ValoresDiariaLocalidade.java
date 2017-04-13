package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "valores_diaria_id_seq", sequenceName = "valores_diaria_id_seq", schema="diaria", initialValue = 1, allocationSize = 1)
@Table(name="valores_diaria_localidade", schema="diaria")
public class ValoresDiariaLocalidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "valores_diaria_id_seq")
	private Long id;
	
	private Integer codigo;
	
	private String deslocamento;
	
	private BigDecimal alojamento;
	
	private BigDecimal pernoite;
	
	private BigDecimal retornoTrezeAsDezenove;
	
	private BigDecimal retornoAposDezenove;
	
	private BigDecimal deslocamentoSeisAsDoze;
	
	private BigDecimal deslocamentoMaisDeDoze;
	
	private Integer indiceUfesp;
	
	@OneToOne
	@JoinColumn(name="id_diaria")
	private Diaria diaria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDeslocamento() {
		return deslocamento;
	}

	public void setDeslocamento(String deslocamento) {
		this.deslocamento = deslocamento;
	}

	public BigDecimal getPernoite() {
		return pernoite;
	}

	public void setPernoite(BigDecimal pernoite) {
		this.pernoite = pernoite;
	}

	public BigDecimal getRetornoTrezeAsDezenove() {
		return retornoTrezeAsDezenove;
	}

	public void setRetornoTrezeAsDezenove(BigDecimal retornoTrezeAsDezenove) {
		this.retornoTrezeAsDezenove = retornoTrezeAsDezenove;
	}

	public BigDecimal getRetornoAposDezenove() {
		return retornoAposDezenove;
	}

	public void setRetornoAposDezenove(BigDecimal retornoAposDezenove) {
		this.retornoAposDezenove = retornoAposDezenove;
	}

	public BigDecimal getDeslocamentoSeisAsDoze() {
		return deslocamentoSeisAsDoze;
	}

	public void setDeslocamentoSeisAsDoze(BigDecimal deslocamentoSeisAsDoze) {
		this.deslocamentoSeisAsDoze = deslocamentoSeisAsDoze;
	}

	public BigDecimal getDeslocamentoMaisDeDoze() {
		return deslocamentoMaisDeDoze;
	}

	public void setDeslocamentoMaisDeDoze(BigDecimal deslocamentoMaisDeDoze) {
		this.deslocamentoMaisDeDoze = deslocamentoMaisDeDoze;
	}

	public Integer getIndiceUfesp() {
		return indiceUfesp;
	}

	public void setIndiceUfesp(Integer indiceUfesp) {
		this.indiceUfesp = indiceUfesp;
	}

	public Diaria getDiaria() {
		return diaria;
	}

	public void setDiaria(Diaria diaria) {
		this.diaria = diaria;
	}

	public BigDecimal getAlojamento() {
		return alojamento;
	}

	public void setAlojamento(BigDecimal alojamento) {
		this.alojamento = alojamento;
	}
	
	
	
	
}

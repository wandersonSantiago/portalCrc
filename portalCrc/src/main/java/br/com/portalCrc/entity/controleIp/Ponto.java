package br.com.portalCrc.entity.controleIp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.entity.Setor;

@Entity
@SequenceGenerator(name = "ponto_id_seq", sequenceName = "ponto_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="ponto", schema="controle_ip")
public class Ponto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ponto_id_seq")
	private Long id;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	@Column(name="numero", nullable=false)
	private String numero;
	@ManyToOne
	@JoinColumn(name="setor", nullable=false)
	private Setor setor;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	
	

}

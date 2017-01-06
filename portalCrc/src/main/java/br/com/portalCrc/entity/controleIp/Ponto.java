package br.com.portalCrc.entity.controleIp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private int numero;
	@Column(name="setor", nullable=false)
	private String setor;
	@Column(name="unidade", nullable=false)
	private String unidade;
	
	
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	
	

}

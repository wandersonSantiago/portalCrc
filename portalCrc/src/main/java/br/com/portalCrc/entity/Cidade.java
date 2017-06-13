package br.com.portalCrc.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_cidade" , schema="comum")
public class Cidade {

	@Id
	@Column(name="cida_id")
	private Integer id;
	@Column(name="cida_cidade")
	private String nome;
	@ManyToOne
	@JoinColumn(name="cida_id_estado")
	private Estado estado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}

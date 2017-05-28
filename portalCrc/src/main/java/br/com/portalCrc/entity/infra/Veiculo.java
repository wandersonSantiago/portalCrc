package br.com.portalCrc.entity.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.entity.Cidade;
import br.com.portalCrc.entity.Unidade;

@Entity
@SequenceGenerator(name = "veiculo_id_seq", sequenceName = "veiculo_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="veiculo", schema="principal")
public class Veiculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_id_seq")
	private Long id;
	
	@Column(name="placa")
	private String placa;
	
	@ManyToOne
	@JoinColumn(name="id_modelo")
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name="id_cor")
	private Cor cor;

	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	
	
}

package br.com.portalCrc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.enums.TipoSetorEnum;

@Entity
@SequenceGenerator(name = "setor_id_seq", sequenceName = "setor_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="setor", schema="principal")
public class Setor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setor_id_seq")
	private Long id;
	
	@Column(name="nome", nullable = false)
	private String nome;
	@Enumerated(EnumType.STRING)
	private TipoSetorEnum tipoSetor;		
	@OneToOne
	@JoinColumn(name="id_tipoUnidade")
	private TipoUnidade tipoUnidade;
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}


	public TipoSetorEnum getTipoSetor() {
		return tipoSetor;
	}


	public void setTipoSetor(TipoSetorEnum tipoSetor) {		
		this.tipoSetor = tipoSetor ;
	}


	public TipoUnidade getTipoUnidade() {
		return tipoUnidade;
	}


	public void setTipoUnidade(TipoUnidade tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
}

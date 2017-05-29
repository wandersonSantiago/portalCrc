package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;

@Entity
@SequenceGenerator(name = "funcionario_diaria_id_seq", sequenceName = "funcionario_diaria_id_seq", initialValue = 1, allocationSize = 1)
@Table(name="funcionario_diaria", schema="diaria")
public class FuncionarioDiaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_diaria_id_seq")
	private Long id;
	
		
	@OneToMany(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_item_diaria",nullable = false)
	private List<ItemDiaria> itens;	

	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name="tota_valor_diaria")
	private BigDecimal totalValorDiaria;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario_diaria")
	private Diaria diaria;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemDiaria> getItens() {
		return itens;
	}

	public void setItens(List<ItemDiaria> itens) {
		this.itens = itens;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Diaria getDiaria() {
		return diaria;
	}

	public void setDiaria(Diaria diaria) {
		this.diaria = diaria;
	}
		
	public BigDecimal getTotalValorDiaria() {
		return totalValorDiaria;
	}

	public void setTotalValorDiaria(BigDecimal totalValorDiaria) {
		this.totalValorDiaria = totalValorDiaria;
	}
	

}
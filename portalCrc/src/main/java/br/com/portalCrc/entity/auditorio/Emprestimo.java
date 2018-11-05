package br.com.portalCrc.entity.auditorio;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;

@Entity
@SequenceGenerator(name = "emprestimo_id_seq", sequenceName = "emprestimo_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="emprestimo", schema="principal")

public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimo_id_seq")
	private Long id;
	
		
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE})	
	@JoinTable(name = "emprestimo_equipamento", schema="principal", joinColumns = @JoinColumn(name = "id_equipamento"), 
	inverseJoinColumns = @JoinColumn(name = "id_emprestimo"))
	private List<Equipamento> equipamento;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;
	
	@NotNull (message="Data do emprestimo não pode ser nula")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEmprestimo;
	
	@NotNull (message="Data da devolução não pode ser nula")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDevolucao;
	
	@NotNull (message="evento realizado não pode ser nulo")
	@Column(name="evento")
	private String evento;
	
	//@Column(name = "descricao")
	//private String descricao;
	
	private Date dataCadastro;
		
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuarioCadastro;
	
	@NotNull (message="Unidade não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="id_unidade_cadastro")
	private Unidade unidadeCadastro;
	
	@Enumerated(EnumType.STRING)
	private StatusEmprestimo status;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoEnum tipo;



	public StatusEmprestimo getStatus() {
		return status;
	}

	public void setStatus(StatusEmprestimo status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Equipamento> getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(List<Equipamento> equipamento) {
		this.equipamento = equipamento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}
	
	

//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}

	public TipoEquipamentoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEquipamentoEnum tipo) {
		this.tipo = tipo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usurioCadastro) {
		this.usuarioCadastro = usurioCadastro;
	}

	public Unidade getUnidadeCadastro() {
		return unidadeCadastro;
	}

	public void setUnidadeCadastro(Unidade unidadeCadastro) {
		this.unidadeCadastro = unidadeCadastro;
	}
	
	

}

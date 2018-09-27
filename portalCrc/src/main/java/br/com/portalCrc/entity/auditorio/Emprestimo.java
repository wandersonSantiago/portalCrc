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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.entity.controleIp.Equipamento;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;
import br.com.portalCrc.enums.chamado.PrioridadeChamado;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;

@Entity
@SequenceGenerator(name = "emprestimo_id_seq", sequenceName = "emprestimo_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="emprestimo", schema="principal")

public class Emprestimo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emprestimo_id_seq")
	private Long id;
	
	@OneToMany(cascade = { CascadeType.ALL})
	@JoinTable(name = "emprestimo_equipamento", schema="principal", joinColumns = @JoinColumn(name = "id_equipamento"), 
	inverseJoinColumns = @JoinColumn(name = "id_emprestimo"))
	private List<Equipamento> equipamento;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;
	
	@NotNull (message="Data do emprestimo não pode ser nula")
	@Temporal(TemporalType.DATE)
	private Date dataEmprestimo;
	
	@Temporal(TemporalType.DATE)
	private Date dataDevolucao;
	
	@NotNull (message="evento realizado não pode ser nulo")
	@Column(name="evento")
	private String evento;
	
	private Date dataCadastro;
		
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuarioCadastro;
	
	@OneToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@Enumerated(EnumType.STRING)
	private StatusEmprestimo status;	

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

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	

}

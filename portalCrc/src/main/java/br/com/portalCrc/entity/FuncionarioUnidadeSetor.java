package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "funcionario_unidade_setor_id_seq", sequenceName = "funcionario_unidade_setor_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="funcionario_unidade_setor", schema="principal")
public class FuncionarioUnidadeSetor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_unidade_setor_id_seq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="id_funcionario_unidade")
	private FuncionarioUnidade funcionarioUnidade;
	
	@OneToOne
	@JoinColumn(name="id_setor")
	private Setor setor;

	public FuncionarioUnidade getFuncionarioUnidade() {
		return funcionarioUnidade;
	}

	public void setFuncionarioUnidade(FuncionarioUnidade funcionarioUnidade) {
		this.funcionarioUnidade = funcionarioUnidade;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}

package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.portalCrc.entity.Cargo;
import br.com.portalCrc.entity.Funcao;
import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import lombok.Data;
@Data
@Entity
@SequenceGenerator(name = "funcionario_diaria_id_seq", sequenceName = "funcionario_diaria_id_seq", initialValue = 1, allocationSize = 1)
@Table(name="funcionario_diaria", schema="diaria")
public class FuncionarioDiaria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_diaria_id_seq")
	private Long id;

	@NotNull (message="Conta do Funcionario não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="id_funcionario_conta")
	private ContaFuncionarioDiaria contaFuncionario;
	
	@OneToOne
	private Cargo cargo;
	@OneToOne
	private Setor setor;
	@OneToOne
	private Funcao funcao;
	
	@NotNull (message="Unidade não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	

	private BigDecimal totalValorDiaria = new BigDecimal("0");	
	private BigDecimal totalPago = new BigDecimal("0");	
	private BigDecimal glosada = new BigDecimal("0");
	
	@NotNull (message="Diaria não pode ser nula")
	@ManyToOne
	@JoinColumn(name="id_diaria")
	private Diaria diaria;
	
	
	private String banco;
	private String agencia;
	private String conta;
	private BigDecimal salarioAtual;
	private Integer indiceUfesp;
	private Double limiteCemPorCento;
	private String decreto;
	
	
}

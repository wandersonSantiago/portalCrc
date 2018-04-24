package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
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

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Usuario;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "conta_funcionario_diaria_id_seq", sequenceName = "conta_funcionario_diaria_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "conta_funcionario_diaria", schema = "diaria")
public class ContaFuncionarioDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_funcionario_diaria_id_seq")
	private Long id;

	@NotNull(message = "Funcionario não pode ser nulo")
	@OneToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@NotNull(message = "Usuario não pode ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private Usuario usuarioCadastro;

	@NotNull(message = "Data não pode ser nulo")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@NotNull(message = "banco não pode ser nulo")
	@Column(name = "banco")
	private String banco;

	@NotNull(message = "agencia não pode ser nulo")
	@Column(name = "agencia")
	private String agencia;

	@NotNull(message = "conta não pode ser nulo")
	@Column(name = "conta")
	private String conta;

	@NotNull(message = "salario não pode ser nulo")
	@Column(name = "salario_atual")
	private BigDecimal salarioAtual;

	@NotNull(message = "indice Ufesp não pode ser nulo")
	@Column(name = "indice_ufesp")
	private Integer indiceUfesp;

	@NotNull(message = "limitede diaria não pode ser nulo")
	@Column(name = "limiteCemPorCento")
	private Double limiteCemPorCento;

	private String decreto;
	
	@Column(name = "status")
	private boolean status;

	public ContaFuncionarioDiaria() {
		setDecreto(this.decreto);
	}

	public void setDecreto(String decreto) {
		if(decreto == null || decreto.contentEquals("")) {
			this.decreto = "Declaro  que  a  importância  recebida  a  título  de diárias durante  o  mês    declarado  não ultrapassou  o  limite previsto  no \"caput\" do  artigo  8º  do Decreto nº 48.292/2003 e que não recebi esta Diária por outra Unidade";
		}else {
			this.decreto = decreto;
		}
	}
}

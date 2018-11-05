package br.com.portalCrc.entity.auditorio;

import java.util.Date;

import javax.persistence.CascadeType;
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
import br.com.portalCrc.entity.Pessoa;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;


@Entity
@SequenceGenerator(name = "reserva_id_seq", sequenceName = "reserva_id_seq", schema="pincipal", initialValue = 1, allocationSize = 1)
@Table(name="reserva", schema="principal")

public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_id_seq")
	private long id;
	
	@NotNull (message="Data da reserva não pode ser nula")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataReserva;
	@NotNull (message="Horario de reserva inicio não pode ser nulo")
	@Column(name="hora_reserva_inicio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaReservaInicio;
	
	@NotNull (message="Horario de reserva termino não pode ser nulo")
	@Column(name="hora_reserva_termino")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horaReservaTermino;
		
	@OneToOne
	@JoinColumn(name ="id_auditorio", nullable = true)
	private Auditorio auditorio;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;
	
	@NotNull (message="evento realizado não pode ser nulo")
	@Column(name="evento")
	private String evento;
	
	@NotNull (message="Unidade não pode ser nulo")
	@ManyToOne
	@JoinColumn(name="id_unidade_cadastro")
	private Unidade unidadeCadastro;
	
	
	@OneToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuarioCadastro;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Date getHoraReservaInicio() {
		return horaReservaInicio;
	}

	public void setHoraReservaInicio(Date horaReservaInicio) {
		this.horaReservaInicio = horaReservaInicio;
	}

	public Date getHoraReservaTermino() {
		return horaReservaTermino;
	}

	public void setHoraReservaTermino(Date horaReservaTermino) {
		this.horaReservaTermino = horaReservaTermino;
	}
	
	public Auditorio getAuditorio() {
		return auditorio;
	}

	public void setAuditorio(Auditorio auditorio) {
		this.auditorio = auditorio;
	}
	
	

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Unidade getUnidadeCadastro() {
		return unidadeCadastro;
	}

	public void setUnidadeCadastro(Unidade unidadeCadastro) {
		this.unidadeCadastro = unidadeCadastro;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

		
}

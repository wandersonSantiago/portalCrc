package br.com.portalCrc.entity.chamado;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Usuario;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "mensagem_id_seq", sequenceName = "mensagem_id_seq", schema="chamado", initialValue = 1, allocationSize = 1)
@Table(name="mensagem", schema="chamado")
public class Mensagem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagem_id_seq")
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_chamado")
	private Chamado chamado;
	
	
	@Column(name="texto")
	private String texto;
	
	@CreationTimestamp
	@Column(name="data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	protected Usuario usuario;
	
	@Column(name="arquivo")
	private Byte arquivo;

	private Boolean lido;
	
	
	public Mensagem() {
	}
	
	public Mensagem(Long id, Chamado chamado, String texto, Date data, Usuario usuario) {
		super();
		this.id = id;
		this.chamado = chamado;
		this.texto = texto;
		this.data = data;
		this.usuario = usuario;
	}

	public Boolean getLido() {
		if(lido == null) {
			return false;
		}
		return lido;
	}


	
}

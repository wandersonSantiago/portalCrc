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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.util.ConverteTextoUpperCase;

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
	
	
	@Column(name="data")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	protected Usuario usuario;
	
	@Column(name="arquivo")
	private Byte arquivo;
	
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = ConverteTextoUpperCase.converte(texto);
	}
	public Date getData() {
		return data;
	}
	public void setData(Date localDate) {
		this.data = localDate;
	}
	public Byte getArquivo() {
		return arquivo;
	}
	public void setArquivo(Byte arquivo) {
		this.arquivo = arquivo;
	}
	public Chamado getChamado() {
		return chamado;
	}
	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}

package br.com.portalCrc.entity.chamado;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.portalCrc.entity.Usuario;

@Entity
@Table(name="mensagem")
public class Mensagem extends AbstractPersistable<Long>{

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_chamado")
	private Chamado chamado;
	
	
	@Column(name="texto")
	private String texto;
	
	
	@Column(name="data")
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	protected Usuario usuario;
	
	@Column(name="arquivo")
	private Byte arquivo;
	
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime localDate) {
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
	
	
	
}

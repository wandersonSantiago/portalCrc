package br.com.portalCrc.entity.chamado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.chamado.PrioridadeChamado;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.findControll.View;

@Entity
@Table(name="chamado")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Chamado extends AbstractPersistable<Long>{
	
	
	@Enumerated(EnumType.STRING)
	protected StatusChamado status;
	

	@Column(name="titulo")
	protected String titulo;
	
	@Column(name="lido")
	protected Boolean lido;

	@Column(name="data_abertura")
	protected LocalDateTime dataAbertura;
	
	
	
	@Column(name="data_fechamento")
	protected  Date dataFechamento;
	
	@ManyToOne
	@JoinColumn(name="id_unidade")
	protected Unidade unidade;
	
	
	@Enumerated(EnumType.STRING)
	protected PrioridadeChamado prioridade;
	
	
	@ManyToOne
	@JoinColumn(name="id_usuarioSolicitante")
	protected Usuario usuarioSolicitante;
	
	
	@ManyToOne
	@JoinColumn(name="id_usuarioAtendente") 
	protected Usuario usuarioAtendente;
	
	@Column(name="descricao_servico")
	protected String descricaoServico;
	
	
	@ManyToOne
	@JoinColumn(name="id_setor")
	protected Setor setor;	
	
	
	@OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL)
	protected List<Mensagem> mensagens;
	
	
	
	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	public StatusChamado getStatus() {
		return status;
	}
	public void setStatus(StatusChamado status) {
		this.status = status;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDateTime localDate) {
		this.dataAbertura = localDate;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public PrioridadeChamado getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(PrioridadeChamado prioridade) {
		this.prioridade = prioridade;
	}
	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}
	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}
	public Usuario getUsuarioAtendente() {
		return usuarioAtendente;
	}
	public void setUsuarioAtendente(Usuario usuarioAtendente) {
		this.usuarioAtendente = usuarioAtendente;
	}
	public String getDescricaoServico() {
		return descricaoServico;
	}
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public Boolean getLido() {
		return lido;
	}
	public void setLido(Boolean lido) {
		this.lido = lido;
	}
	
	

}

package br.com.portalCrc.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name="dadosUnidades")
public class DadosUnidade extends AbstractPersistable<Long>{

	
	@Column(name="nome")
	private String nome;
	@Column(name="mini_nome")
	private String miniNome;
	@OneToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	@Column(name="uge")
	private String uge;
	@OneToMany(mappedBy = "dadosUnidade", cascade = CascadeType.ALL)
	private Collection<ListaTelefonica> listaTelefones;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMiniNome() {
		return miniNome;
	}
	public void setMiniNome(String miniNome) {
		this.miniNome = miniNome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getUge() {
		return uge;
	}
	public void setUge(String uge) {
		this.uge = uge;
	}
	public Collection<ListaTelefonica> getListaTelefones() {
		return listaTelefones;
	}
	public void setListaTelefones(Collection<ListaTelefonica> listaTelefones) {
		this.listaTelefones = listaTelefones;
	} 
	
	
	
}

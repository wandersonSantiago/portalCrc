package br.com.portalCrc.entity.controleIp;

import javax.persistence.Column;
import javax.persistence.Entity;  //control shift + o serve para buscar as dependencias
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;

@Entity 
@SequenceGenerator(name = "tipo_ip_id_seq", sequenceName = "tipo_ip_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="tipo_ip", schema="controle_ip")

public class TipoIp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_ip_id_seq") 
	private Long id;
	
	
	@Column(name = "descricao", nullable=false)
	private String descricao; 
	
	@OneToOne
	@JoinColumn(name="id_unidade")
	private Unidade unidade;
	
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private Usuario usuarioCadastro;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	

}

package br.com.portalCrc.entity.controleIp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;  //control shift + o serve para buscar as dependencias
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity 
@SequenceGenerator(name = "tipo_ip_id_seq", sequenceName = "tipo_ip_id_seq", schema="controle_ip", initialValue = 1, allocationSize = 1) 
@Table(name="tipo_ip", schema="controle_ip")

public class TipoIp  implements Serializable{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_ip_id_seq") 
	private Long id;
	
	
	@Column(name = "descricao", nullable=false)
	private String descricao; 
	
	
	@Column(name="id_unidade")
	private Long unidade;
	
	
	@Column(name="id_usuario_cadastro")
	private Long usuarioCadastro;
	
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

	public Long getUnidade() {
		return unidade;
	}

	public void setUnidade(Long unidade) {
		this.unidade = unidade;
	}

	public Long getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Long usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	

}

package br.com.portalCrc.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.portalCrc.enums.ModuloPermissaoEnum;

@Entity
@SequenceGenerator(name = "permissoes_id_seq", sequenceName = "principal.permissoes_id_seq", schema="principal", initialValue = 1, allocationSize = 1)
@Table(name="permissoes", schema="principal")
public class Permissao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissoes_id_seq")
	private Long id;
	
	private String descricao;
	
	
	@Enumerated(EnumType.STRING)
    private ModuloPermissaoEnum modulo;
	

	
	public ModuloPermissaoEnum getModulo() {
		return modulo;
	}
	public void setModulo(ModuloPermissaoEnum modulo) {
		this.modulo = modulo;
	}
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

}

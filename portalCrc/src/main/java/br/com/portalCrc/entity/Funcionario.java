package br.com.portalCrc.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="funcionario")
public class Funcionario  extends AbstractPersistable<Long>{

	
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST} )
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	/*@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private Collection<FuncionarioUnidade> unidades;*/
		
	
	



	/*public Collection<FuncionarioUnidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(Collection<FuncionarioUnidade> unidades) {
		this.unidades = unidades;
	}
*/
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}

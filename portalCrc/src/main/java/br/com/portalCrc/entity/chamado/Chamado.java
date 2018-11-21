package br.com.portalCrc.entity.chamado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.portalCrc.entity.Setor;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.chamado.PrioridadeChamado;
import br.com.portalCrc.enums.chamado.StatusChamado;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "chamado_id_seq", sequenceName = "chamado_id_seq", schema="chamado", initialValue = 1, allocationSize = 1)
@Table(name="chamado", schema = "chamado")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Chamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chamado_id_seq")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	protected StatusChamado status;	

	@ManyToOne
	@JoinColumn(name="id_titulo")
	protected TemaChamado titulo;
	
	@Column(name="lido")
	protected Boolean lido;
	
	@Column(name="texto", columnDefinition="text")
	protected String texto;

	@Column(name="silenciar")
	protected Boolean silenciar;	
	
	@Column(name="data_abertura")
	protected Date dataAbertura;	

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
	
	@ManyToOne
	@JoinColumn(name="id_setor")
	protected Setor setor;		
	
	@OneToMany(mappedBy = "chamado")
	protected List<Mensagem> mensagens = new ArrayList<>();
	@Transient
	private Integer count = 0;
	
	
	public Integer getMensagensNaoLida() {		
		mensagens.forEach(m ->{
			if(m.getLido() == false) {
				count ++;
			}
		});
		return count;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}	
	

	

}

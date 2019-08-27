package br.com.portalCrc.web.controller.auditorio.filters;

import java.util.Date;

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.entity.Usuario;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;
import br.com.portalCrc.enums.controleIp.TipoEquipamentoEnum;
import br.com.portalCrc.pojo.Page;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;


public class AuditorioFilter {
	
		
	
	private Usuario usuarioCadastro; 	
	
	private String unidade;
	
	
	private Unidade unidadeCadastro; 
		
	
	private StatusEmprestimo status;
	
	

	private TipoEquipamentoEnum tipo;
	
	
	private String evento;
	
	
	private Date dataEmprestimo;
	
	
	private Date dataDevolucao;
	
	
	private Funcionario funcionario;
	
	
	private Page page;
}




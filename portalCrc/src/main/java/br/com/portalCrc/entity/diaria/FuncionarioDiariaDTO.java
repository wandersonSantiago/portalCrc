package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class FuncionarioDiariaDTO {
	
	private Long idDiaria;
	private String nomeFuncionario;
	private Long idFuncionario;
	private String cpfFuncionario;
	private String nomeCargo;
	private String nomeSetor;	
	private String nomeUnidade;	
	private String nomeUsuario;
	private String setorUsuario;
	
	
	private Date dataCadastro;
	private BigDecimal totalValorDiaria;
	private BigDecimal glosada;
	private Diaria diaria;
	
	public FuncionarioDiariaDTO(FuncionarioDiaria diaria) {
		this.idDiaria = diaria.getId();
		this.idFuncionario = diaria.getContaFuncionario().getFuncionario().getId();
		this.nomeFuncionario = diaria.getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto();
		this.cpfFuncionario = diaria.getContaFuncionario().getFuncionario().getPessoa().getCpf();
		this.nomeCargo = diaria.getCargo().getDescricao();
		if(diaria.getSetor() != null) {
			this.nomeSetor = diaria.getSetor().getNome();
		}		
		this.nomeUnidade = diaria.getUnidade().getDadosUnidade().getMnemonico();
		this.nomeUsuario = diaria.getUsuarioCadastro().getFuncionario().getPessoa().getNomeCompleto();
		this.setorUsuario = diaria.getUsuarioCadastro().getFuncionario().getSetorAtual().getNome();
		this.dataCadastro = diaria.getDataCadastro();
		this.totalValorDiaria = diaria.getTotalValorDiaria();
		this.glosada = diaria.getGlosada();
		this.diaria = diaria.getDiaria();
	}
	

}

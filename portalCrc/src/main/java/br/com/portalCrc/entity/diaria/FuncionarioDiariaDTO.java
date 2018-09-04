package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.portalCrc.entity.Funcionario;
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
	private String nomeUnidadeCurto;
	private String nomeUsuario;
	private String setorUsuario;
	

	private Date dataCadastro;
	private BigDecimal totalValorDiaria;
	private BigDecimal glosada;
	private Diaria diaria;
	private Double valor;

	private Double valorTotal;
	
	private boolean status;

	private List<ItemDiariaDTO> itens;

	private Date dataInicial;
	private Date dataFinal;
	private int count;

	public FuncionarioDiariaDTO() {

	}

	public FuncionarioDiariaDTO(FuncionarioDiaria diaria) {
		this.idDiaria = diaria.getId();
		this.idFuncionario = diaria.getContaFuncionario().getFuncionario().getId();
		this.nomeFuncionario = diaria.getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto();
		this.cpfFuncionario = diaria.getContaFuncionario().getFuncionario().getPessoa().getCpf();
		this.nomeCargo = diaria.getCargo().getDescricao();
		this.nomeSetor = diaria.getSetor() == null ? "Não consta" : diaria.getSetor().getNome();
		this.nomeUnidade = diaria.getContaFuncionario().getFuncionario().getUnidadeAtual().getDadosUnidade().getMnemonico();
		this.nomeUsuario = diaria.getUsuarioCadastro().getFuncionario().getPessoa().getNomeCompleto();
		this.setorUsuario = diaria.getUsuarioCadastro().getFuncionario().getSetorAtual().getNome();
		this.dataCadastro = diaria.getDataCadastro();
		this.totalValorDiaria = diaria.getTotalValorDiaria();
		this.glosada = diaria.getGlosada();
		this.diaria = diaria.getDiaria();
	}

	public FuncionarioDiariaDTO(Funcionario obj, List<ItemDiariaDTO> itensDTO, int count, Date dataInicial,	Date dataFinal, Double valor, Double valorTotal) {
		this.idFuncionario = obj.getId();
		this.nomeFuncionario = obj.getPessoa().getNomeCompleto();
		this.cpfFuncionario = obj.getPessoa().getCpf();
		this.nomeUnidade = obj.getUnidadeAtual().getDadosUnidade().getNome();
		this.nomeUnidadeCurto = obj.getUnidadeAtual().getDadosUnidade().getMnemonico();
		this.nomeSetor = obj.getSetorAtual() == null ? "Não consta" : obj.getSetorAtual().getNome();
		this.nomeCargo = obj.getCargoAtual().getDescricao();
		this.itens = itensDTO;
		this.count = count;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.valorTotal = valorTotal;
		this.valor = valor;
	}

	public FuncionarioDiariaDTO(FuncionarioDiaria obj, List<ItemDiariaDTO> itensDTO, int count,	Double valor, Double valorTotal) {
		this.idFuncionario = obj.getId();
		this.nomeFuncionario = obj.getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto();
		this.cpfFuncionario = obj.getContaFuncionario().getFuncionario().getPessoa().getCpf();
		this.nomeUnidade = obj.getContaFuncionario().getFuncionario().getUnidadeAtual().getDadosUnidade().getNome();
		this.nomeUnidadeCurto = obj.getContaFuncionario().getFuncionario().getUnidadeAtual().getDadosUnidade().getMnemonico();
		this.nomeSetor = obj.getContaFuncionario().getFuncionario().getSetorAtual() == null ? "Não consta" : obj.getContaFuncionario().getFuncionario().getSetorAtual().getNome();
		this.nomeCargo = obj.getContaFuncionario().getFuncionario().getCargoAtual().getDescricao();
		this.itens = itensDTO;
		this.count = count;
		this.valor = valor;
		this.valorTotal = valorTotal;
		this.diaria = obj.getDiaria();
	}

	
}

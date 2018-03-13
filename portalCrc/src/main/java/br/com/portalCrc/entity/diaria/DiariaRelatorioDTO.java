package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.util.Extenso;

public class DiariaRelatorioDTO {
	

	private BigDecimal valorUfesp;
	private Date dataAbertura;
	private Date dataFechamento;	
	private MesDiariaEnum mes;
	private String observacao;
	
	private String nomeFuncionario;
	private String rg;
	private String cpf;
	private String unidade;
	private String cargo;
	
	
	private BigDecimal salarioAtual;	
	private Integer indiceUfesp;	
	private Double limiteCemPorCento;
	private BigDecimal limiteDiaria;
	private BigDecimal valorDiaria;
	private BigDecimal glosada = new BigDecimal(0);
	private String ultrapassaCinquentaPoCento = "";
	private String naoUltrapassaCinquentaPoCento = "";
	private String banco;	
	private String agencia;	
	private String conta;
	private BigDecimal valorTotalDiaria;
	private String valorPorExtenso;
	
	
	private Iterable<ValoresDiariaLocalidade> valoresLocalidade;
	private ItemDiariaRelatorioDTO itens;
	
	private ValoresDiariaLocalidade ValoresCodigoUm;	
	private ValoresDiariaLocalidade ValoresCodigoDois;
	private ValoresDiariaLocalidade ValoresCodigoTres;
	private ValoresDiariaLocalidade ValoresCodigoQuatro;
	private ValoresDiariaLocalidade ValoresCodigoCinco;
	

	
	public DiariaRelatorioDTO(FuncionarioDiaria funcDiaria, List<ValoresDiariaLocalidade> valoresLocalidade, ItemDiariaRelatorioDTO itenDTO) {
		
		this.valorUfesp = funcDiaria.getDiaria().getValorUfesp();
		this.dataAbertura = funcDiaria.getDiaria().getDataAbertura();
		this.mes = funcDiaria.getDiaria().getMes();
		this.observacao = funcDiaria.getDiaria().getObservacao();
		
		this.nomeFuncionario = funcDiaria.getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto();
		this.rg = funcDiaria.getContaFuncionario().getFuncionario().getPessoa().getRg();
		this.cpf = funcDiaria.getContaFuncionario().getFuncionario().getPessoa().getCpf();
		this.cargo = funcDiaria.getCargo().getDescricao();
		this.unidade = funcDiaria.getUnidade().getDadosUnidade().getMnemonico();
		
		this.salarioAtual = funcDiaria.getContaFuncionario().getSalarioAtual();
		this.indiceUfesp = funcDiaria.getContaFuncionario().getIndiceUfesp();
		this.limiteCemPorCento = funcDiaria.getContaFuncionario().getLimiteCemPorCento();
		this.valorTotalDiaria = funcDiaria.getTotalValorDiaria();
		
		if(funcDiaria.getGlosada() != null) {
			this.glosada = funcDiaria.getGlosada();
		}
		
		
		this.banco = funcDiaria.getContaFuncionario().getBanco();
		this.agencia = funcDiaria.getContaFuncionario().getAgencia();
		this.conta = funcDiaria.getContaFuncionario().getConta();
		this.ValoresCodigoUm = valoresLocalidade.get(0);
		this.ValoresCodigoDois = valoresLocalidade.get(1);
		this.ValoresCodigoTres = valoresLocalidade.get(2);
		this.ValoresCodigoQuatro = valoresLocalidade.get(3);
		this.ValoresCodigoCinco = valoresLocalidade.get(4);
		
		this.valorPorExtenso = new Extenso(funcDiaria.getTotalValorDiaria()).toString();
		verificaLimiteDaDiaria(funcDiaria);
		
		valorDiaria();
		
		this.itens = itenDTO;
		
		
		
	}
	
	public void valorDiaria() {
		this.valorDiaria =  valorUfesp.multiply(new BigDecimal(indiceUfesp)) ;
		
	}
	
	public void verificaLimiteDaDiaria(FuncionarioDiaria funcDiaria) {
		
		if(funcDiaria.getContaFuncionario().getLimiteCemPorCento() == 100) {
			this.limiteDiaria = funcDiaria.getContaFuncionario().getSalarioAtual();
			this.ultrapassaCinquentaPoCento = "X";
		}else {
			BigDecimal divisor = new BigDecimal("2");
			this.limiteDiaria = funcDiaria.getContaFuncionario().getSalarioAtual().divide(divisor);
			this.naoUltrapassaCinquentaPoCento = "X";
		}
		
	}
	
	
	
	public BigDecimal getValorUfesp() {
		return valorUfesp;
	}
	public void setValorUfesp(BigDecimal valorUfesp) {
		this.valorUfesp = valorUfesp;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public MesDiariaEnum getMes() {
		return mes;
	}
	public void setMes(MesDiariaEnum mes) {
		this.mes = mes;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public BigDecimal getSalarioAtual() {
		return salarioAtual;
	}
	public void setSalarioAtual(BigDecimal salarioAtual) {
		this.salarioAtual = salarioAtual;
	}
	public Integer getIndiceUfesp() {
		return indiceUfesp;
	}
	public void setIndiceUfesp(Integer indiceUfesp) {
		this.indiceUfesp = indiceUfesp;
	}
	public Double getLimiteCemPorCento() {
		return limiteCemPorCento;
	}
	public void setLimiteCemPorCento(Double limiteCemPorCento) {
		this.limiteCemPorCento = limiteCemPorCento;
	}
	public BigDecimal getLimiteDiaria() {
		return limiteDiaria;
	}
	public void setLimiteDiaria(BigDecimal limiteDiaria) {
		this.limiteDiaria = limiteDiaria;
	}
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public BigDecimal getGlosada() {
		return glosada;
	}
	public void setGlosada(BigDecimal glosada) {
		this.glosada = glosada;
	}

	public String getUltrapassaCinquentaPoCento() {
		return ultrapassaCinquentaPoCento;
	}

	public void setUltrapassaCinquentaPoCento(String ultrapassaCinquentaPoCento) {
		this.ultrapassaCinquentaPoCento = ultrapassaCinquentaPoCento;
	}

	public String getNaoUltrapassaCinquentaPoCento() {
		return naoUltrapassaCinquentaPoCento;
	}

	public void setNaoUltrapassaCinquentaPoCento(String naoUltrapassaCinquentaPoCento) {
		this.naoUltrapassaCinquentaPoCento = naoUltrapassaCinquentaPoCento;
	}

	public BigDecimal getValorTotalDiaria() {
		return valorTotalDiaria;
	}

	public void setValorTotalDiaria(BigDecimal valorTotalDiaria) {
		this.valorTotalDiaria = valorTotalDiaria;
	}

	public Iterable<ValoresDiariaLocalidade> getValoresLocalidade() {
		return valoresLocalidade;
	}

	public void setValoresLocalidade(Iterable<ValoresDiariaLocalidade> valoresLocalidade) {
		this.valoresLocalidade = valoresLocalidade;
	}

	public ValoresDiariaLocalidade getValoresCodigoUm() {
		return ValoresCodigoUm;
	}

	public void setValoresCodigoUm(ValoresDiariaLocalidade valoresCodigoUm) {
		ValoresCodigoUm = valoresCodigoUm;
	}

	public ValoresDiariaLocalidade getValoresCodigoDois() {
		return ValoresCodigoDois;
	}

	public void setValoresCodigoDois(ValoresDiariaLocalidade valoresCodigoDois) {
		ValoresCodigoDois = valoresCodigoDois;
	}

	public ValoresDiariaLocalidade getValoresCodigoTres() {
		return ValoresCodigoTres;
	}

	public void setValoresCodigoTres(ValoresDiariaLocalidade valoresCodigoTres) {
		ValoresCodigoTres = valoresCodigoTres;
	}

	public ValoresDiariaLocalidade getValoresCodigoQuatro() {
		return ValoresCodigoQuatro;
	}

	public void setValoresCodigoQuatro(ValoresDiariaLocalidade valoresCodigoQuatro) {
		ValoresCodigoQuatro = valoresCodigoQuatro;
	}

	public ValoresDiariaLocalidade getValoresCodigoCinco() {
		return ValoresCodigoCinco;
	}

	public void setValoresCodigoCinco(ValoresDiariaLocalidade valoresCodigoCinco) {
		ValoresCodigoCinco = valoresCodigoCinco;
	}

	public ItemDiariaRelatorioDTO getItens() {
		return itens;
	}

	public void setItens(ItemDiariaRelatorioDTO itens) {
		this.itens = itens;
	}

	public String getValorPorExtenso() {
		return valorPorExtenso;
	}

	public void setValorPorExtenso(String valorPorExtenso) {
		this.valorPorExtenso = valorPorExtenso;
	}




	
	
	
	
	
	

	
	

}

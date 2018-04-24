package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.util.Extenso;
import lombok.Data;

@Data
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
	private BigDecimal saldo;
	private BigDecimal antecipado;
	private String valorPorExtenso;
	private String decreto;
	
	
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
		this.antecipado = funcDiaria.getTotalPago();
		this.saldo = funcDiaria.getTotalValorDiaria().subtract(this.antecipado);
		
		this.decreto = funcDiaria.getDecreto();
		
		if(funcDiaria.getGlosada() != null) {
			this.glosada = funcDiaria.getGlosada();
		}
		
		
		this.banco = funcDiaria.getBanco();
		this.agencia = funcDiaria.getAgencia();
		this.conta = funcDiaria.getConta();
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
			this.limiteDiaria = funcDiaria.getSalarioAtual();
			this.ultrapassaCinquentaPoCento = "X";
		}else {
			BigDecimal divisor = new BigDecimal("2");
			this.limiteDiaria = funcDiaria.getSalarioAtual().divide(divisor);
			this.naoUltrapassaCinquentaPoCento = "X";
		}
		
	}
	
	
}

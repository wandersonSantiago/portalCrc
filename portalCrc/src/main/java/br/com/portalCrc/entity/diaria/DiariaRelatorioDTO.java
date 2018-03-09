package br.com.portalCrc.entity.diaria;

import java.math.BigDecimal;
import java.util.Date;

import br.com.portalCrc.enums.diaria.MesDiariaEnum;

public class DiariaRelatorioDTO {
	

	private BigDecimal valorUfesp;
	private Date dataAbertura;
	private Date dataFechamento;	
	private MesDiariaEnum mes;
	private String observacao;
	private BigDecimal valorDiaria;
	

	
	private String banco;	
	private String agencia;	
	private String conta;
		
	private BigDecimal salarioAtual;	
	private Integer indiceUfesp;	
	private Double limiteCemPorCento;
	private BigDecimal limiteDiaria;
	
	
	private String nomeFuncionario;
	private String rg;
	private String cpf;
	private String unidade;
	private String cargo;
	
	
	
	
	public DiariaRelatorioDTO(Diaria diaria) {
		this.valorUfesp = diaria.getValorUfesp();
		this.dataAbertura = diaria.getDataAbertura();
		this.observacao = diaria.getObservacao();
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
	
	
	
	
	
	

	
	

}

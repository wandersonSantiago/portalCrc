package br.com.portalCrc.util;

import br.com.portalCrc.enums.chamado.StatusChamado;

public class Result {

	private String result;
	private String prioridade;
	private Integer valor;
	private String user;
	private String solicitante;
	private String atendente;
	private String idUnidade;
	private boolean silenciar;
	private boolean status;

	
	public Result() {
		super();
	}

	public Result(String result, Integer valor) {
		this.result = result;
		this.valor = valor;
	}

	public String getResult() {
		return result;
	}

	public Integer getValor() {
		return valor;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isSilenciar() {
		return silenciar;
	}

	public void setSilenciar(boolean silenciar) {
		this.silenciar = silenciar;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(StatusChamado status) {
		this.status = status == StatusChamado.EM_ANDAMENTO ? true : false;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	

	
}

package br.com.portalCrc.enums;

public enum TipoUnidade {

	CDP ("Centro de Detenção Provisório"),
	CR ("Centro de Ressocialização"),
	Penitenciaria ("Penitenciaria"),
	CPP("Centro de Progressão Penitenciaria");
	
	private String descricao;

	TipoUnidade(String descricao){
		this.descricao =  descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	 
}

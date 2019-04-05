package br.com.portalCrc.enums.auditorio;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEmprestimo {

	EMPRESTADO("emprestado"), DEVOLVIDO("devolvido");

	private String descricao;

	
	StatusEmprestimo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@JsonCreator
	public static StatusEmprestimo fromObject(Map<String, String> data){
		return StatusEmprestimo.getEnum(data.get("descricao"));
	}

	public static StatusEmprestimo getEnum(String valor) {
		for (StatusEmprestimo status : StatusEmprestimo.values()) {
			if (status.descricao.equals(valor)) {
				return status;
			}
		}
		throw new NullPointerException("Status não encontrado para a descrição :" + valor);
	}

	/*
	 * @JsonValue public List<StatusEmprestimo> getValues(){ List<StatusEmprestimo>
	 * list = new ArrayList<>(); for(StatusEmprestimo obj :
	 * StatusEmprestimo.values()) { list.add(obj); }return list; }
	 */
}

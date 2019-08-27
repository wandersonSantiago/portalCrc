package br.com.portalCrc.entity.chamado;

import java.util.Date;

import br.com.portalCrc.entity.Usuario;
import lombok.Data;

@Data
public class MensagemDTO {

	private Long id;	
	private ChamadoTi chamadoTi;
	private Chamado chamado;
	private String texto;
	private Date data;
	protected Usuario usuario;
	private Byte arquivo;
	
	public MensagemDTO() {
	}
	
	public MensagemDTO(Mensagem mensagem) {
		super();
		this.id = mensagem.getId();
		this.chamado = mensagem.getChamado();
		this.texto = mensagem.getTexto();
		this.data = mensagem.getData();
		this.usuario = mensagem.getUsuario();
	}


	public void setChamado() {
		this.chamado = this.chamadoTi;
	}
	
	public Chamado getChamado() {
		this.chamado = this.chamadoTi;
		return this.chamado;
	}
}

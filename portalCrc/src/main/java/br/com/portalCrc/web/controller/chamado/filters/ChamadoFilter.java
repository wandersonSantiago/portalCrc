package br.com.portalCrc.web.controller.chamado.filters;

import java.util.Date;

import br.com.portalCrc.entity.chamado.TemaChamado;
import br.com.portalCrc.enums.chamado.PrioridadeChamado;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.enums.chamado.TipoEquipamentoChamado;
import br.com.portalCrc.pojo.Page;
import lombok.Getter;
import lombok.Setter;

public class ChamadoFilter {

	@Getter @Setter
	private String mensagem;
	@Getter @Setter
	private TemaChamado titulo;
	@Getter @Setter
	private String descricao;
	@Getter @Setter
	private StatusChamado status;
	@Getter @Setter
	private TipoEquipamentoChamado tipoEquipamentos;
	@Getter @Setter
	private PrioridadeChamado prioridade;
	@Getter @Setter
	private Date dataCadastroDe;
	@Getter @Setter
	private Date dataCadastroAte;
	@Getter @Setter
	private Date dataLimiteDe;
	@Getter @Setter
	private Date dataLimiteAte;
	@Getter @Setter
	private Date dataFechamentoDe;
	@Getter @Setter
	private Date dataFechamentoAte;
	
	@Getter @Setter
	private Page page;
}

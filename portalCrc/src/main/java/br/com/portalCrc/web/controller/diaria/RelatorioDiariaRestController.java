package br.com.portalCrc.web.controller.diaria;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.diaria.DiariaRelatorioDTO;
import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.entity.diaria.ItemDiariaRelatorioDTO;
import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.ValoresDiariaLocalidadeRepository;
import br.com.portalCrc.service.diaria.FuncionarioDiariaService;
import br.com.portalCrc.service.diaria.ItemDiariaService;
import br.com.portalCrc.service.diaria.MensagemException;
import br.com.portalCrc.service.diaria.RelatorioDiariaService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/rest/diaria/relatorio")
public class RelatorioDiariaRestController {

	
	@Autowired
	private RelatorioDiariaService relatorioDiariaService;	
	@Autowired
	private ItemDiariaService itemDiariaService;
	
	@Autowired
	private FuncionarioDiariaService funcionarioDiariaService;
	
	@Autowired
	private ValoresDiariaLocalidadeRepository valoresDiariaLocalidadeRepository;
	
	 
	 	@GetMapping(value = "/imprimir")
		@ResponseBody
		public byte[] imprimir(@RequestParam(value = "id", required = true) Long id,
				@RequestParam(value = "tipo", required = true , defaultValue="ADMINISTRATIVO") TipoDiariaEnum tipo, HttpServletResponse response) {
			
			response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		    response.setContentType("application/pdf");
			
			FuncionarioDiaria funcionarioDiaria = funcionarioDiariaService.buscaPorId(id);
			List<ValoresDiariaLocalidade> valores = (List<ValoresDiariaLocalidade>) valoresDiariaLocalidadeRepository
					.findByIndiceUfespAndDiaria_id(funcionarioDiaria.getContaFuncionario().getIndiceUfesp(), funcionarioDiaria.getDiaria().getId());
			
			List<ItemDiaria> itens = (List<ItemDiaria>) itemDiariaService.findByFuncionarioDiaria_idAndTipo(funcionarioDiaria.getId(), tipo);
			
				ItemDiariaRelatorioDTO itenDTO = new ItemDiariaRelatorioDTO(itens);
				Double doubleValor = itemDiariaService.somaValorDoFuncionarioDiariaId(funcionarioDiaria.getId(), funcionarioDiaria.getDiaria().getId(), tipo);
				BigDecimal valor = new BigDecimal(doubleValor);
				funcionarioDiaria.setTotalValorDiaria(valor);
				funcionarioDiaria.getTotalValorDiaria().add(valor);
			DiariaRelatorioDTO relatorio = new DiariaRelatorioDTO(funcionarioDiaria, valores,itenDTO);
			
			
			try {
				return relatorioDiariaService.generateReport(Arrays.asList(relatorio));				
			} catch (JRException e) {
				e.printStackTrace();
				throw new MensagemException("Erro ao gerar pdf: " + e.getMessage());
			}
			
			
		}
		
}

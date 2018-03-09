package br.com.portalCrc.web.controller.diaria;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.entity.diaria.DiariaRelatorioDTO;
import br.com.portalCrc.service.diaria.DiariaService;
import br.com.portalCrc.service.diaria.MensagemException;
import br.com.portalCrc.service.diaria.RelatorioDiariaService;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/rest/diaria/relatorio")
public class RelatorioDiariaRestController {

	@Autowired
	private RelatorioDiariaService relatorioDiariaService;	
	@Autowired
	private DiariaService diariaService;
	
	 
	 	@GetMapping(value = "/{id}/imprimir")
		@ResponseBody
		public byte[] imprimir(@PathVariable Long id, HttpServletResponse response) {
			
			response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		    response.setContentType("application/pdf");
			Diaria diaria = diariaService.buscaPorId(id);
			
			DiariaRelatorioDTO relatorio = new DiariaRelatorioDTO(diaria);
			
			try {
				return relatorioDiariaService.generateReport(Arrays.asList(relatorio));
			} catch (JRException e) {
				e.printStackTrace();
				throw new MensagemException("Erro ao gerar pdf: " + e.getMessage());
			}
			
			
		}
		
}

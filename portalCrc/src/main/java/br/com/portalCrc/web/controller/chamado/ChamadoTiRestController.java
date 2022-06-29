package br.com.portalCrc.web.controller.chamado;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.openqa.selenium.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.enums.chamado.PrioridadeChamado;
import br.com.portalCrc.enums.chamado.StatusChamado;
import br.com.portalCrc.enums.chamado.TipoTema;
import br.com.portalCrc.jasper.JasperReportsService;
import br.com.portalCrc.jasper.RelatorioUtil;
import br.com.portalCrc.service.chamado.ChamadoTiService;
import br.com.portalCrc.web.controller.chamado.filters.ChamadoFilter;

@RestController
@RequestMapping("/rest/chamado/chamadoTi")
public class ChamadoTiRestController {

	
	@Autowired
	private ChamadoTiService chamadoTiService;
	@Autowired
	private JasperReportsService jasperReportsService;
	@Autowired
	private RelatorioUtil relatorioUtil;
	
	
	@PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?ADMIN')")
	 @GetMapping(value = "/suporte/relatorio/dataInicial/{dataInicial}/dataFinal/{dataFinal}")
		public ResponseEntity<Iterable<ChamadoTi>> relatorio(@PathVariable Date dataInicial, @PathVariable Date dataFinal) {
			Iterable<ChamadoTi> chamadoTi = chamadoTiService.relatorio(dataInicial, dataFinal);
			return new ResponseEntity<Iterable<ChamadoTi>>(chamadoTi, HttpStatus.OK);
		}
	 
	@PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?ADMIN')")
	@GetMapping(value = "/suporte/relatorio/dataInicial/{dataInicial}/dataFinal/{dataFinal}/titulo/{idTitulo}")
	public ResponseEntity<Iterable<ChamadoTi>> relatorioPorDataETitulo(@PathVariable Date dataInicial,
			@PathVariable Date dataFinal, @PathVariable Long idTitulo) {
		Iterable<ChamadoTi> chamado = chamadoTiService.relatorioPorDataETitulo(dataInicial, dataFinal, idTitulo);
		return new ResponseEntity<Iterable<ChamadoTi>>(chamado, HttpStatus.OK);
	}
	 
	@PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?ADMIN')")
	 @GetMapping(value="/suporte/relatorio")
		public ResponseEntity<Page<ChamadoTi>> relatorio(@RequestParam(defaultValue="0", required=false) int page
				,@RequestParam(defaultValue="0", required=false) int maxResults) {
			Page<ChamadoTi> chamadoTi = chamadoTiService.relatorio(new PageRequest(page, maxResults));
			return new ResponseEntity<Page<ChamadoTi>>(chamadoTi, HttpStatus.OK);
		}
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_USUARIO','ROLE_?ADMIN')")
	 @PostMapping
	 public void salvar(@RequestPart(value="file", required = false ) MultipartFile file, @RequestPart("chamado")  ChamadoTi obj) throws IOException{
		 if(file != null) {
			 String  base64 = Base64.encodeBase64String(file.getBytes());
				obj.setImagem(base64);
		 }		
		 chamadoTiService.salvarEditar(obj);
	 }
	
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?ADMIN')")
	 @PutMapping(value="/servicos")
	 public void servicos(@RequestBody ChamadoTi chamadoTi){
		 chamadoTiService.servicos(chamadoTi);
	 }
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_USUARIO','ROLE_?ADMIN')")
	 @PutMapping(value="/alterar")
	 public void alterar(@RequestBody ChamadoTi chamadoTi){
		 chamadoTiService.salvarEditar(chamadoTi);
	 }

	
	 @ResponseStatus(HttpStatus.OK)
	 @GetMapping(value = "/buscaPorId/{id}")
		public ChamadoTi buscarPorId(@PathVariable Long id) {
			return chamadoTiService.buscaPorId(id);
		}
	 
	 @ResponseStatus(HttpStatus.OK)
	 @GetMapping(value = "/count")
		public Long count() {
			return chamadoTiService.count();
		}
	 
	 @ResponseStatus(HttpStatus.OK)
	 @GetMapping(value = "/prioridade")
		public List<PrioridadeChamado> prioridade() {
			return Arrays.asList(PrioridadeChamado.values());
		}
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_USUARIO','ROLE_?ADMIN')")
	 @PutMapping(value="/mensagem")
	 public void mensagem(@RequestBody ChamadoTi chamadoTi){
		 chamadoTiService.mensagens(chamadoTi);
	 }
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?ADMIN')")
	 @PutMapping(value="/atender")
	 public void atender(@RequestBody ChamadoTi chamadoTi){
		 chamadoTiService.atenderChamado(chamadoTi);
	 }
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?CHAMADO_USUARIO','ROLE_?ADMIN')")
	 @PutMapping(value="/fechar")
	 public void fechar(@RequestBody ChamadoTi chamadoTi){
		 chamadoTiService.fecharChamado(chamadoTi);
	 }
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PreAuthorize("hasAnyRole('ROLE_?CHAMADO_INFORMATICA_TECNICO','ROLE_?ADMIN')")
	 @PutMapping(value="/silenciar")
	 public ChamadoTi silenciarTrue(@RequestBody ChamadoTi chamadoTi){
		 chamadoTiService.silenciarChamado(chamadoTi);
		 return chamadoTi;
	 }
	 
	 @ResponseStatus(HttpStatus.OK)
 	 @GetMapping(value = "/status")
	 public List<StatusChamado> status() {
		return Arrays.asList(StatusChamado.values());
	 }
 
	 @ResponseStatus(HttpStatus.OK)
 	 @GetMapping(value = "/equipamento/tipo")
	 public List<TipoTema> tipoTema() {
		return Arrays.asList(TipoTema.values());
	 }		
	 
	 @PostMapping(value = "/filter")
		public ResponseEntity<Page<ChamadoTi>> filtro(@RequestBody ChamadoFilter cotacaoFilter) {
		 	Page<ChamadoTi> list = chamadoTiService.pageFilter(cotacaoFilter, new  PageRequest(
					cotacaoFilter.getPage().getPage(), cotacaoFilter.getPage().getLinesPerPage(), Direction.valueOf(cotacaoFilter.getPage().getDirection()), cotacaoFilter.getPage().getOrderBy()));
			return ResponseEntity.ok().body(list);
		}
		
		
		@PostMapping(value = "/imprimir")
		@ResponseBody
		public byte[] filtroPdf(@RequestBody ChamadoFilter cotacaoFilter, HttpServletResponse response) {
			response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		    response.setContentType("application/pdf");
		    List<ChamadoTi> cotacoes = (List<ChamadoTi>) chamadoTiService.listFilter(cotacaoFilter);
		try {	
			return jasperReportsService.generateReport(cotacoes, relatorioUtil.caminhoChamadoPDF() , relatorioUtil.caminhoMapaDeLogos() );	
				} catch (Exception e) {
				e.printStackTrace();
				throw new NotFoundException("Erro ao gerar pdf: " + e.getMessage());
			}		
		}
}

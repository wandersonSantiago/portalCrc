package br.com.portalCrc.web.controller.diaria;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.entity.diaria.DiariaDTO;
import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.FuncionarioDiariaDTO;
import br.com.portalCrc.entity.diaria.ItemDiaria;
import br.com.portalCrc.entity.diaria.ItemDiariaDTO;
import br.com.portalCrc.enums.diaria.MesDiariaEnum;
import br.com.portalCrc.enums.diaria.TipoDiariaEnum;
import br.com.portalCrc.jasper.JasperReportsService;
import br.com.portalCrc.jasper.RelatorioUtil;
import br.com.portalCrc.service.diaria.DiariaService;
import br.com.portalCrc.service.diaria.FuncionarioDiariaService;
import br.com.portalCrc.service.diaria.ItemDiariaService;
import br.com.portalCrc.service.diaria.MensagemException;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/rest/diaria")
public class DiariaRestController {

	@Autowired
	private DiariaService diariaService;
	@Autowired
	private FuncionarioDiariaService funcionarioDiariaService;
	@Autowired
	private ItemDiariaService itemDiariaService;
	@Autowired
	private JasperReportsService relatorioDiariaService;
	
	private RelatorioUtil relatorioUtil = new RelatorioUtil();
	
	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PostMapping
	 public ResponseEntity<Diaria> salvar(@RequestBody Diaria diaria){
		diariaService.salvaOuAltera(diaria);
		 return new ResponseEntity<Diaria>(HttpStatus.CREATED);
	 }
	
	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<Diaria> alterar(@RequestBody Diaria diaria){
		diariaService.alterar(diaria);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PutMapping(value="/encerrar")
	public ResponseEntity<Diaria> encerrar(@RequestBody Diaria diaria){
		diariaService.encerrar(diaria);
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	
	@GetMapping
	public ResponseEntity<Page<Diaria>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<Diaria> diaria = diariaService.lista(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Diaria>>(diaria, HttpStatus.OK);
	}

	
	@GetMapping(value="/diariasEmAberto")
	public ResponseEntity<Diaria> diariasEmAberto(){
		Diaria diaria	= diariaService.diariasEmAberto();
		return new ResponseEntity<Diaria>(diaria, HttpStatus.OK);
	}

	
	 @GetMapping(value = "/{id}")
		public ResponseEntity<Diaria> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<Diaria>(diariaService.buscaPorId(id), HttpStatus.OK);
	 }
	 @GetMapping(value = "/mes")
		public ResponseEntity<Iterable<MesDiariaEnum>> mesDiariaEnum() {
			Iterable<MesDiariaEnum> mesDiariaEnum = Arrays.asList(MesDiariaEnum.values());
			return new ResponseEntity<Iterable<MesDiariaEnum>>(mesDiariaEnum, HttpStatus.OK);
		}
	 @GetMapping("/unidade")
	 public List<DiariaDTO> findByUnidade_id(){		 
		 List<Diaria> diarias = diariaService.findByUnidade_id();
		 List<DiariaDTO> diariaDTO = diarias.stream().map(obj -> new DiariaDTO(obj)).collect(Collectors.toList());
		 return diariaDTO;
	 }
	 
	 @GetMapping("/unidades/mes/")
	 public ResponseEntity<List<Diaria>> findByCoordenadoriaMes(@RequestParam MesDiariaEnum mes){
		 List<Diaria> diarias = diariaService.findByCoordenadoriaMes(mes);
		 return new ResponseEntity<List<Diaria>>(diarias, HttpStatus.OK);
	 }
	 
		@GetMapping(value = "/funcionarios/imprimir")
		@ResponseBody
		public byte[] imprimir(@RequestParam(value="idDiaria", required=true) Long idDiaria, HttpServletResponse response) {			
			response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		    response.setContentType("application/pdf");
			
			List<FuncionarioDiaria> funcionariosDiaria = funcionarioDiariaService.findAllDiaria(idDiaria);
			
			List<FuncionarioDiariaDTO> funcionariosDiariaDTO = funcionariosDiaria.stream().map(obj -> new FuncionarioDiariaDTO(obj)).collect(Collectors.toList());
			
			funcionariosDiariaDTO.forEach(funcionarioDiariaDTO ->{
				List<ItemDiaria> itens = itemDiariaService.findByFuncionarioDiaria_idAndTipo(funcionarioDiariaDTO.getIdDiaria(), TipoDiariaEnum.ADMINISTRATIVO);
				List<ItemDiariaDTO> itensDTO = itens.stream().map(obj -> new ItemDiariaDTO(obj)).collect(Collectors.toList());
				funcionarioDiariaDTO.setItens(itensDTO);
			});
			
			try {
				return relatorioDiariaService.generateReport(Arrays.asList(funcionariosDiariaDTO), relatorioUtil.caminhoArquivoDiariaFuncionarios(), relatorioUtil.caminhoMapaDeLogos());
			} catch (JRException e) {
				e.printStackTrace();
				throw new MensagemException("Erro ao gerar pdf: " + e.getMessage());
			}
			
			
		}
		
}

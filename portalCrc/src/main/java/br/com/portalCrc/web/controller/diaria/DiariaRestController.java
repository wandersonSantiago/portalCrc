package br.com.portalCrc.web.controller.diaria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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

import br.com.portalCrc.entity.Funcionario;
import br.com.portalCrc.entity.Usuario;
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
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.service.FuncionarioService;
import br.com.portalCrc.service.diaria.DiariaService;
import br.com.portalCrc.service.diaria.FuncionarioDiariaService;
import br.com.portalCrc.service.diaria.ItemDiariaService;
import br.com.portalCrc.service.diaria.MensagemException;
import br.com.portalCrc.util.DateUtil;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/rest/diaria")
public class DiariaRestController {

	@Autowired
	private DiariaService diariaService;
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private FuncionarioDiariaService funcionarioDiariaService;
	@Autowired
	private ItemDiariaService itemDiariaService;
	@Autowired
	private JasperReportsService relatorioDiariaService;

	@Autowired
	private RelatorioUtil relatorioUtil;

	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PostMapping
	public ResponseEntity<Diaria> salvar(@RequestBody Diaria diaria) {
		diariaService.salvaOuAltera(diaria);
		return new ResponseEntity<Diaria>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PutMapping
	public ResponseEntity<Diaria> alterar(@RequestBody Diaria diaria) {
		diariaService.alterar(diaria);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?ADMIN')")
	@PutMapping(value = "/encerrar")
	public ResponseEntity<Diaria> encerrar(@RequestBody Diaria diaria) {
		diariaService.encerrar(diaria);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Page<Diaria>> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		Page<Diaria> diaria = diariaService.lista(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Diaria>>(diaria, HttpStatus.OK);
	}

	@GetMapping(value = "/diariasEmAberto")
	public ResponseEntity<Diaria> diariasEmAberto() {
		Diaria diaria = diariaService.diariasEmAberto();
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
	public List<DiariaDTO> findByUnidade_id() {
		List<Diaria> diarias = diariaService.findByUnidade_id();
		List<DiariaDTO> diariaDTO = diarias.stream().map(obj -> new DiariaDTO(obj)).collect(Collectors.toList());
		return diariaDTO;
	}

	@GetMapping("/unidades/mes/")
	public ResponseEntity<List<Diaria>> findByCoordenadoriaMes(@RequestParam MesDiariaEnum mes) {
		List<Diaria> diarias = diariaService.findByCoordenadoriaMes(mes);
		return new ResponseEntity<List<Diaria>>(diarias, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS', 'ROLE_?DIARIA_DEP_PESSOAL','ROLE_?ADMIN')")
	@GetMapping(value = "/funcionarios/imprimir")
	@ResponseBody
	public byte[] imprimir(@RequestParam(value = "idFuncionario", required = false) Long idFuncionario,
			@RequestParam(value = "dataInicial", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataInicial,
			@RequestParam(value = "dataFinal", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataFinal,
			HttpServletResponse response) {
		response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		response.setContentType("application/pdf");

		Usuario usuario = SessionUsuario.getInstance().getUsuario();
		
		Date dInicial = DateUtil.validaDataInicialParaPesquisa(dataInicial, dataFinal);
		Date dFinal = DateUtil.seDataForNulaRetornaDataAtual(dataFinal);
		
		List<Funcionario> funcionarios = (idFuncionario == null) ? funcionarios = funcionarioService.findAllUnidadeId() : Arrays.asList(funcionarioService.buscaPorId(idFuncionario));

		List<FuncionarioDiariaDTO> funcionariosDiariaDTO = new ArrayList<>();

		int count = 0;
		Double valorTotal =0.0;
		for(Funcionario funcionario : funcionarios) {	
			List<ItemDiaria> itens = itemDiariaService.findByFuncionarioDiariaContaFuncionarioFuncionario_idAndDataSaidaGreaterThanEqualAndDataSaidaLessThanEqual(
							funcionario.getId(), dInicial, dFinal);			
			if (!itens.isEmpty()) {
				count ++;
				List<ItemDiariaDTO> itensDTO = itens.stream().map(obj -> new ItemDiariaDTO(obj)).collect(Collectors.toList());	
				Double valor = itemDiariaService.somaValorDoFuncionarioPorData(funcionario.getId(), dInicial, dFinal);
				valorTotal = valorTotal + valor;
				funcionariosDiariaDTO.add(new FuncionarioDiariaDTO(funcionario, itensDTO, count, dInicial,dFinal, valor, valorTotal));
			}if(itens.isEmpty() && funcionarios.size() == 1){
				throw new MensagemException("Não existem lançamentos para o funcionario : " + funcionario.getPessoa().getNomeCompleto() );
			}			
		}
		try {
			
			String caminhoArquivo = usuario.hasRole("DIARIA_FINANCAS") ? relatorioUtil.caminhoArquivoDiariaFuncionarios() : relatorioUtil.caminhoArquivoDiariaDepartamentoPessoal();
			HashMap<String, Object> map = usuario.hasRole("DIARIA_FINANCAS") ? relatorioUtil.subRelatorioDiariaFinancas(): relatorioUtil.subRelatorioDiariaDepartamentoPessoal();
			
			return relatorioDiariaService.generateReport(funcionariosDiariaDTO,	caminhoArquivo, map );
		} catch (JRException e) {
			e.printStackTrace();
			throw new MensagemException("Erro ao gerar pdf: " + e.getMessage());
		}
	}

	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS', 'ROLE_?DIARIA_DEP_PESSOAL','ROLE_?ADMIN')")
	@GetMapping(value = "/funcionarios")
	@ResponseBody
	public List<FuncionarioDiariaDTO> funcionariosPorData(
			@RequestParam(value = "idFuncionario", required = false) Long idFuncionario,
			@RequestParam(value = "dataInicial", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataInicial,
			@RequestParam(value = "dataFinal", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dataFinal) {

		Date dInicial = DateUtil.validaDataInicialParaPesquisa(dataInicial, dataFinal);
		Date dFinal = DateUtil.seDataForNulaRetornaDataAtual(dataFinal);
		
		List<Funcionario> funcionarios = (idFuncionario == null) ? funcionarios = funcionarioService.findAllUnidadeId() : Arrays.asList(funcionarioService.buscaPorId(idFuncionario));
		
		List<FuncionarioDiariaDTO> funcionariosDiariaDTO = new ArrayList<>();

		int count = 0;
		Double valorTotal =0.0;
		for(Funcionario funcionario : funcionarios) {

			 List<ItemDiaria>	itens = itemDiariaService.findByFuncionarioDiariaContaFuncionarioFuncionario_idAndDataSaidaGreaterThanEqualAndDataSaidaLessThanEqual(
							funcionario.getId(), dInicial, dFinal);				
			count ++;
			if (!itens.isEmpty()) {				
				List<ItemDiariaDTO> itensDTO = itens.stream().map(obj -> new ItemDiariaDTO(obj)).collect(Collectors.toList());
				Double valor = itemDiariaService.somaValorDoFuncionarioPorData(funcionario.getId(), dInicial, dFinal);
				valorTotal = valorTotal + valor;
				funcionariosDiariaDTO.add(new FuncionarioDiariaDTO(funcionario, itensDTO, count, dInicial,dFinal, valor, valorTotal));
			}
			if(itens.isEmpty() && funcionarios.size() == 1){
				throw new MensagemException("Não existem lançamentos para o funcionario : " + funcionario.getPessoa().getNomeCompleto() );
			}
		}
		return funcionariosDiariaDTO;

	}

	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS','ROLE_?DIARIA_DEP_PESSOAL', 'ROLE_?ADMIN')")
	@GetMapping(value = "/mes/funcionarios")
	@ResponseBody
	public List<FuncionarioDiariaDTO> funcionariosPorDiaria(@RequestParam(value = "idDiaria", required = true) Long idDiaria,
			@RequestParam(value = "tipo", required = true , defaultValue="ADMINISTRATIVO") TipoDiariaEnum tipo) {

		List<FuncionarioDiaria> funcionarios = funcionarioDiariaService.findAllDiaria(idDiaria);
		 
		List<FuncionarioDiariaDTO> funcionariosDiariaDTO = new ArrayList<>();
		Double valorTotal = itemDiariaService.somaValorDiariaIdPorTipo(idDiaria, tipo);
		int count = 0;
		for(FuncionarioDiaria funcionario : funcionarios) {
			
			 List<ItemDiaria>	itens = itemDiariaService.findByFuncionarioDiariaIdAndTipo(funcionario.getId(), tipo);				
			
			if (!itens.isEmpty()) {		
				count ++;
				List<ItemDiariaDTO> itensDTO = itens.stream().map(obj -> new ItemDiariaDTO(obj)).collect(Collectors.toList());
				Double valor = itemDiariaService.somaValorDoFuncionarioDiariaId(funcionario.getId(), idDiaria, tipo);
				valor = valor - funcionario.getGlosada().doubleValue();
				valorTotal = valorTotal - funcionario.getGlosada().doubleValue();
				funcionariosDiariaDTO.add(new FuncionarioDiariaDTO(funcionario, itensDTO, count, valor, valorTotal));
			}
			if(itens.isEmpty() && funcionarios.size() == 1){
				throw new MensagemException("Não existem lançamentos para o funcionario : " + funcionario.getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto() );
			}
		}
		return funcionariosDiariaDTO;

	}
	
	@PreAuthorize("hasAnyRole( 'ROLE_?DIARIA_FINANCAS', 'ROLE_?DIARIA_DEP_PESSOAL','ROLE_?ADMIN')")
	@GetMapping(value = "/mes/funcionarios/imprimir")
	@ResponseBody
	public byte[] imprimirDiariaPorID(@RequestParam(value = "idDiaria", required = false) Long idDiaria,
			@RequestParam(value = "tipo", required = true , defaultValue="ADMINISTRATIVO") TipoDiariaEnum tipo
			,	HttpServletResponse response) {
		response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		response.setContentType("application/pdf");

		Usuario usuario = SessionUsuario.getInstance().getUsuario();
	
		
		List<FuncionarioDiaria> funcionarios = funcionarioDiariaService.findAllDiaria(idDiaria);
		 
		List<FuncionarioDiariaDTO> funcionariosDiariaDTO = new ArrayList<>();
		Double valorTotal = itemDiariaService.somaValorDiariaIdPorTipo(idDiaria, tipo);

		int count = 0;
		for(FuncionarioDiaria funcionario : funcionarios) {
			
			List<ItemDiaria>	itens = itemDiariaService.findByFuncionarioDiariaIdAndTipo(funcionario.getId(), tipo);				
			
			if (!itens.isEmpty()) {	
				count ++;
				List<ItemDiariaDTO> itensDTO = itens.stream().map(obj -> new ItemDiariaDTO(obj)).collect(Collectors.toList());
				Double valor = itemDiariaService.somaValorDoFuncionarioDiariaId(funcionario.getId(), idDiaria, tipo);
				valor = valor - funcionario.getGlosada().doubleValue();
				valorTotal = valorTotal - funcionario.getGlosada().doubleValue();
				funcionariosDiariaDTO.add(new FuncionarioDiariaDTO(funcionario, itensDTO, count, valor, valorTotal));
			}
			if(itens.isEmpty() && funcionarios.size() == 1){
				throw new MensagemException("Não existem lançamentos para o funcionario : " + funcionario.getContaFuncionario().getFuncionario().getPessoa().getNomeCompleto() );
			}
		}
		try {
			
			String caminhoArquivo = usuario.hasRole("DIARIA_FINANCAS") ? relatorioUtil.caminhoArquivoDiariaFuncionarios() : relatorioUtil.caminhoArquivoDiariaDepartamentoPessoal();
			HashMap<String, Object> map = usuario.hasRole("DIARIA_FINANCAS") ? relatorioUtil.subRelatorioDiariaFinancas(): relatorioUtil.subRelatorioDiariaDepartamentoPessoal();
			
			return relatorioDiariaService.generateReport(funcionariosDiariaDTO,	caminhoArquivo, map );
		} catch (JRException e) {
			e.printStackTrace();
			throw new MensagemException("Erro ao gerar pdf: " + e.getMessage());
		}
	}
}

package br.com.portalCrc.service.diaria;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.entity.diaria.DiariaRelatorioDTO;
import br.com.portalCrc.repository.diaria.DiariaRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RelatorioDiariaService {

	@Autowired
	private DiariaRepository diariaRepository;	
	
	
	public Diaria buscaPorId(Long id){
		return diariaRepository.findOne(id);
	}
		
	public byte[] generateReport(List<?> diarias) throws JRException {
		if (diarias == null || diarias.isEmpty()) {
			throw new MensagemException("Lista de credenciais não pode ser vazia ");
		}

		
		
		// TODO só para guardar o código
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorio/mapa-diaria.jrxml"));
		JRDataSource datasource = new JRBeanCollectionDataSource(diarias, true);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("brasao", getClass().getResource("/relatorio/imagens/brasao.jpg").toString());
	//	hashMap.put("Coordenador", nomeCoordenador(user.getUnidadePricipal().getCoordenadoria().getId()));*/
		jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, datasource);

		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		return baos.toByteArray();
	}
}

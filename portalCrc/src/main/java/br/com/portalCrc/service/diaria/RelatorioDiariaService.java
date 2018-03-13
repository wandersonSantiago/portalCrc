package br.com.portalCrc.service.diaria;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.Diaria;
import br.com.portalCrc.entity.diaria.DiariaRelatorioDTO;
import br.com.portalCrc.entity.diaria.ItemDiariaRelatorioDTO;
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
		
	public byte[] generateReport(List<Object> list) throws JRException {
		if (list == null || list.isEmpty()) {
			throw new MensagemException("Lista de credenciais não pode ser vazia ");
		}

		
		
		// TODO Alterar deixar mais generico
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/relatorio/mapa-diaria2.jrxml"));
		JRDataSource datasource = new JRBeanCollectionDataSource(list, true);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("Sap", getClass().getResource("/relatorio/imagens/sap.jpg").toString());
		hashMap.put("REPORT_LOCALE", new Locale("pt", "BR")); 
		jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, datasource);

		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		return baos.toByteArray();
	}


}

package br.com.portalCrc.service.auditorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.auditorio.Emprestimo;
import br.com.portalCrc.entity.chamado.ChamadoTi;
import br.com.portalCrc.entity.controleIp.Ip;
import br.com.portalCrc.entity.controleIp.TipoIp;
import br.com.portalCrc.enums.auditorio.StatusEmprestimo;
import br.com.portalCrc.enums.controleIp.StatusIp;
import br.com.portalCrc.pojo.ConverteData;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.auditorio.EmprestimoRepository;
import br.com.portalCrc.service.diaria.MensagemException;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)

public class EmprestimoService {
	
	@Autowired		
	private EmprestimoRepository emprestimoRepository;
		
		@Transactional(readOnly = false)
		public void salvarEditar(Emprestimo emprestimo){
			
			emprestimo.setDataCadastro(new Date());
//			System.out.println("========================================================================================"
//					+ "==============================================================================================="
//					+ "==============================================================================================="
//					+ ""
//					+ "==============================================================================");
//			emprestimo.getEquipamento().forEach(System.out :: println);
//			
			emprestimo.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			emprestimo.setStatus(StatusEmprestimo.EMPRESTADO);
			
			//emprestimo.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
			emprestimoRepository.save(emprestimo);
		}
		
		public Collection<Emprestimo> lista(){
			return emprestimoRepository.findAll();
		}
		
		public Emprestimo buscaPorId(Long id){
			return emprestimoRepository.findOne(id);
		}
		
				
		
		public Page<Emprestimo>buscar(String texto, Pageable page) {
			texto = texto.replaceAll("[./-]","");
			Page<Emprestimo> list;
			if (texto.matches("[0-9]+")) {
				 list = emprestimoRepository.findById(new Long(texto), page);
			} else {
				 list =  emprestimoRepository.findByEventoIgnoreCaseContaining(texto, page);
			}
			if(list == null || list.getNumberOfElements() < 1){
				throw new MensagemException("Busca não encontrada,  para este funcionario! " + texto);
			}
			return list;
	}
		
		public Page<Emprestimo> findAll(PageRequest pageRequest) {
			return emprestimoRepository.findAll(pageRequest);
		}

		
		//Médtodo para alterar somente status do emprestimo
		@Transactional(readOnly=false)
		public void alterarStatus(Emprestimo emprestimo) {
			Emprestimo emp = emprestimoRepository.findOne(emprestimo.getId());
			emp.setStatus(emprestimo.getStatus());
			
		}
		

}

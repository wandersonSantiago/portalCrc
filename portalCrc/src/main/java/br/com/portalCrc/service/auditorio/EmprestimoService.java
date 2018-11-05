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
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.auditorio.EmprestimoRepositoy;
import br.com.portalCrc.service.diaria.MensagemException;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)

public class EmprestimoService {
	
	@Autowired		
	private EmprestimoRepositoy emprestimoRepositoy;
		
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
			//emprestimo.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
			emprestimoRepositoy.save(emprestimo);
		}
		
		public Collection<Emprestimo> lista(){
			return emprestimoRepositoy.findAll();
		}
		
		public Emprestimo buscaPorId(Long id){
			return emprestimoRepositoy.findOne(id);
		}
		
		
		public Page<Emprestimo>buscar(String texto, Pageable page) {
			texto = texto.replaceAll("[./-]","");
			Page<Emprestimo> list;
			if (texto.matches("[0-9]+")) {
				 list = emprestimoRepositoy.findById(new Long(texto), page);
			} else {
				 list =  emprestimoRepositoy.findByEventoIgnoreCaseContaining(texto, page);
			}
			if(list == null || list.getNumberOfElements() < 1){
				throw new MensagemException("Busca não encontrada,  para este funcionario! " + texto);
			}
			return list;
	}
		
		public Page<Emprestimo> findAll(PageRequest pageRequest) {
			return emprestimoRepositoy.findAll(pageRequest);
		}	

}

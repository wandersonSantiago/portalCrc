package br.com.portalCrc.service.auditorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.auditorio.Emprestimo;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.auditorio.EmprestimoRepositoy;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)

public class EmprestimoService {
	
	@Autowired		
	private EmprestimoRepositoy emprestimoRepositoy;
		
		@Transactional(readOnly = false)
		public void salvarEditar(Emprestimo emprestimo){
			emprestimo.setDataCadastro(new Date());
			emprestimo.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
			emprestimo.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
			emprestimoRepositoy.save(emprestimo);
		}
		
		public Collection<Emprestimo> lista(){
			return emprestimoRepositoy.findAll();
		}
		
		public Emprestimo buscaPorId(Long id){
			return emprestimoRepositoy.findOne(id);
		}

}

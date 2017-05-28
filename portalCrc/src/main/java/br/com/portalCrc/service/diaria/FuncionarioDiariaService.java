package br.com.portalCrc.service.diaria;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.diaria.FuncionarioDiaria;
import br.com.portalCrc.entity.diaria.ValoresDiariaLocalidade;
import br.com.portalCrc.enums.diaria.StatusDiariaEnum;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.diaria.FuncionarioDiariaRepository;
import br.com.portalCrc.repository.diaria.ValoresDiariaLocalidadeRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioDiariaService {
	

	@Autowired
	private FuncionarioDiariaRepository funcionarioDiariaRepository;
	
	@Autowired
	private ValoresDiariaLocalidadeRepository valoresDiaraRepository;

	
	
	@Transactional(readOnly = false)
	public void salvaOuAltera(FuncionarioDiaria funcionarioDiaria){
		funcionarioDiaria.setDataCadastro(new Date());
		funcionarioDiaria.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		funcionarioDiaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		for(int  i=0; i <funcionarioDiaria.getItens().size(); i++){
			funcionarioDiaria.getItens().get(i).setFuncionarioDiaria(funcionarioDiaria);
			funcionarioDiaria.getItens().get(i).setDataCadastro(new Date());
			
		}
		if(funcionarioDiaria.getDiaria().getStatus()  == StatusDiariaEnum.ABERTO){
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void altera(FuncionarioDiaria funcionarioDiaria){
		if(funcionarioDiaria.getDiaria().getStatus()  == StatusDiariaEnum.ABERTO){
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}
		
	}
	
	public FuncionarioDiaria buscaPorId(Long id){
		return funcionarioDiariaRepository.findOne(id);
	}
	
	public List<FuncionarioDiaria> listaUnidade(Long id){
		return funcionarioDiariaRepository.findByUnidade_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
	}
	
	public List<FuncionarioDiaria> listaCoordenadoria(Long id){
		return funcionarioDiariaRepository.findByUnidadeCoordenadoria_idAndDiaria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId(), id);
	}
	public List<FuncionarioDiaria> lista(){
		return funcionarioDiariaRepository.findAll();
	}

	public Iterable<FuncionarioDiaria> listaSecretaria(Long id) {		
		return funcionarioDiariaRepository.findByUnidadeCoordenadoriaSecretaria_id(id);
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		funcionarioDiariaRepository.delete(id);
		
	}

	public Iterable<ValoresDiariaLocalidade> valoresDiaria(Integer id, Long idDiaria) {
		return valoresDiaraRepository.findByIndiceUfespAndDiaria_id(id, idDiaria);
	}

	public Iterable<FuncionarioDiaria> findByUnidade_idAndDiaria_id(Long id) {
		return funcionarioDiariaRepository.findByUnidade_idAndDiaria_id(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
	}

	

}

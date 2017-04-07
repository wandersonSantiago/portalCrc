package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Unidade;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.UnidadeRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UnidadeService {

	
	@Autowired
	private UnidadeRepository unidadeRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Unidade unidade){
		unidade.setDataCadastro(new Date());
		unidade.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		unidadeRepository.save(unidade);
	}
	
	public Collection<Unidade> lista(){
		return unidadeRepository.findAll();
	}
	
	public Unidade buscaPorId(Long id){
		return unidadeRepository.findOne(id);
	}

	public Iterable<Unidade> buscaUnidadePorId(Long id) {		
		return unidadeRepository.buscaUnidadePorCoordenadoria(id);
	}

	public Iterable<Unidade> buscaUnidadePorCoordenadoria(Long id) {
		return unidadeRepository.findByCoordenadoria_id(SessionUsuario.getInstance().getUsuario().getUnidade().getCoordenadoria().getId());
	}

	public Iterable<Unidade> listaUnidadeCoordenadoria() {
		return unidadeRepository.findByTipoUnidadeMnemonico("COORDENADORIA");
	}

	

	public Iterable<Unidade> buscarUnidadePorCoordenadoriaPorTipo(Long id, String tipo) {
		return unidadeRepository.findByCoordenadoria_IdByTipoUnidadeMnemonico(id, tipo);
	}
}

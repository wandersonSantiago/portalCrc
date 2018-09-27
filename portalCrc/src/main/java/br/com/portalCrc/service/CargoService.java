package br.com.portalCrc.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Cargo;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.CargoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Cargo cargo){
		cargo.setSecretaria(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getSecretaria());
		cargo.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		cargo.setDataCadastro(new Date());
		cargoRepository.save(cargo);
	}
	
	public Collection<Cargo> lista(){
		return cargoRepository.findBySecretaria_idOrderByDescricao(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getSecretaria().getId());
	}
	
	public Cargo buscaPorId(Long id){
		return cargoRepository.findOne(id);
	}

}

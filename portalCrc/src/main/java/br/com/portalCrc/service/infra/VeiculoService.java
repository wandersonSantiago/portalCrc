package br.com.portalCrc.service.infra;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.infra.Cor;
import br.com.portalCrc.entity.infra.Marca;
import br.com.portalCrc.entity.infra.Modelo;
import br.com.portalCrc.entity.infra.Veiculo;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.infra.CorRepository;
import br.com.portalCrc.repository.infra.MarcaRepository;
import br.com.portalCrc.repository.infra.ModeloRepository;
import br.com.portalCrc.repository.infra.VeiculoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private ModeloRepository modeloRepository;
	
	@Autowired
	private CorRepository corRepository;
	
	@Transactional(readOnly = false)
	public void salvarEditar(Veiculo veiculo){
		veiculo.setUnidade(SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual());
		veiculoRepository.save(veiculo);
	}
	
	public Collection<Veiculo> lista(){
		return veiculoRepository.findAll();
	}
	
	public Veiculo buscaPorId(Long id){
		return veiculoRepository.findOne(id);
	}

	public List<Marca> findAllMarcas() {
		return marcaRepository.findAll();
	}

	public List<Modelo> findAllModelo(Long idMarca) {
		return modeloRepository.findByMarca_id(idMarca);
	}

	public List<Cor> findAllCores() {
		return corRepository.findAll();
	}

	public List<Veiculo> findByUnidade_id(Long idUnidade) {
		return veiculoRepository.findByUnidade_id(idUnidade);
	}

}

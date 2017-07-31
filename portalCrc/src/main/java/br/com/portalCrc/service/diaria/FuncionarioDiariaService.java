package br.com.portalCrc.service.diaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Usuario;
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
	public void salvaOuAltera(FuncionarioDiaria funcionarioDiaria) {
		Usuario usuario = SessionUsuario.getInstance().getUsuario();
		BigDecimal total = new BigDecimal(0);
		funcionarioDiaria.setDataCadastro(new Date());
		funcionarioDiaria.setUnidade(usuario.getFuncionario().getUnidadeAtual());
		funcionarioDiaria.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		funcionarioDiaria.setCargo(usuario.getFuncionario().getCargoAtual());
		funcionarioDiaria.setSetor(usuario.getFuncionario().getSetorAtual());

		funcionarioDiaria.setTotalValorDiaria(total);
		if (funcionarioDiaria.getDiaria().getStatus() == StatusDiariaEnum.ABERTO) {
			funcionarioDiariaRepository.save(funcionarioDiaria);			
		} else {
			throw new MensagemException("Não foi possivel realizar este lançamento, Este mês esta encerrado!!!");
		}
	}
	
	@Transactional(readOnly = false)
	public void altera(FuncionarioDiaria funcionarioDiaria) {
		if (funcionarioDiaria.getDiaria().getStatus() == StatusDiariaEnum.ABERTO) {
			funcionarioDiariaRepository.save(funcionarioDiaria);
		}

	}

	public FuncionarioDiaria buscaPorId(Long id) {
		return funcionarioDiariaRepository.findOne(id);
	}

	public List<FuncionarioDiaria> listaUnidade(Long id) {
		List<FuncionarioDiaria> lista = funcionarioDiariaRepository.findByUnidade_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);

		if (lista.isEmpty() || lista == null) {
			throw new MensagemException("Unidade não tem lançamento nesta diaria! ");
		}

		return lista;
	}

	public List<FuncionarioDiaria> listaCoordenadoria(Long id) {
		List<FuncionarioDiaria> lista = funcionarioDiariaRepository.findByUnidade_coordenadoria_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getCoordenadoria().getId(),
				id);

		if (lista.isEmpty() || lista == null) {
			throw new MensagemException("Coordenadoria não tem lançamento nesta diaria! ");
		}

		return lista;
	}

	public List<FuncionarioDiaria> lista() {
		return funcionarioDiariaRepository.findAll();
	}

	public Iterable<FuncionarioDiaria> listaSecretaria(Long id) {
		Iterable<FuncionarioDiaria> lista = funcionarioDiariaRepository.findByDiaria_id(id);

		if (lista == null) {
			throw new MensagemException("Secretaria não tem lançamento nesta diaria! ");
		}

		return lista;
	}

	@Transactional(readOnly = false)
	public void excluir(Long id) {
		funcionarioDiariaRepository.delete(id);

	}

	public Iterable<ValoresDiariaLocalidade> valoresDiaria(Integer id, Long idDiaria) {
		return valoresDiaraRepository.findByIndiceUfespAndDiaria_id(id, idDiaria);
	}

	public Iterable<FuncionarioDiaria> findByUnidade_idAndDiaria_id(Long id) {
		return funcionarioDiariaRepository.findByUnidade_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), id);
	}

	public FuncionarioDiaria findByUnidade_idAndContaFuncionario_idAndDiaria_id(Long idFuncionario, Long idDiaria) {
		FuncionarioDiaria funcionario = funcionarioDiariaRepository.findByUnidade_idAndContaFuncionario_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), idFuncionario,
				idDiaria);
		if (funcionario == null) {
			throw new MensagemException("Favor Conferir os Dados!!!");
		}
		return funcionario;
	}

	public Page<FuncionarioDiaria> buscar(String texto, PageRequest pageRequest) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
		Page<FuncionarioDiaria> list = funcionarioDiariaRepository
				.findByUnidade_idAndContaFuncionario_funcionario_pessoa_nomeCompletoIgnoreCaseContainingOrContaFuncionario_funcionario_pessoa_cpfContaining(
						idUnidade, "%" + texto + "%", pageRequest);
		if (list.getNumberOfElements() == 0) {
			throw new MensagemException("Nenhum resultado encontrado para: " + texto);
		}

		return list;
	}

	public FuncionarioDiaria findByUnidade_idAndContaFuncionarioFuncionario_idAndDiaria_id(Long idFuncionario,
			Long idDiaria) {
		FuncionarioDiaria funcionario = funcionarioDiariaRepository.findByUnidade_idAndContaFuncionario_funcionario_idAndDiaria_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId(), idFuncionario,
				idDiaria);
		if (funcionario == null) {
			throw new MensagemException("Favor Conferir os Dados!!!");
		}
		return funcionario;
	}

}

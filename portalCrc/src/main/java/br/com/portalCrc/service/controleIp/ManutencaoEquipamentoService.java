package br.com.portalCrc.service.controleIp;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.controleIp.ManutencaoEquipamento;
import br.com.portalCrc.pojo.SessionUsuario;
import br.com.portalCrc.repository.ControleIp.ManutencaoEquipamentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ManutencaoEquipamentoService {

	@Autowired
	private ManutencaoEquipamentoRepository manutencaoEquipamentoRepository;

	public Collection<ManutencaoEquipamento> lista() {
		return manutencaoEquipamentoRepository.findByEquipamento_unidade_id(
				SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId());
	}

	public ManutencaoEquipamento buscaPorId(Long id) {
		return manutencaoEquipamentoRepository.findOne(id);
	}

	public Iterable<ManutencaoEquipamento> findByStatus(Boolean status) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();
		return manutencaoEquipamentoRepository.findByStatusAndEquipamento_unidade_id(status, idUnidade);
	}

	public Iterable<ManutencaoEquipamento> findByStatusAndEquipamento_unidade_idAndDataPreventivaBETWEENDataPreventiva(
			Boolean status) {
		Long idUnidade = SessionUsuario.getInstance().getUsuario().getFuncionario().getUnidadeAtual().getId();

		Date dataFinal = dataFinalPrioridade();
		return manutencaoEquipamentoRepository
				.findByStatusAndEquipamento_unidade_idAndDataPreventivaBETWEENDataPreventiva(dataFinal, status,
						idUnidade);
	}

	@Transactional(readOnly = false)
	public void salvar(ManutencaoEquipamento manutencaoEquipamento) {
		ManutencaoEquipamento item = manutencaoEquipamentoRepository
				.findTop1ByEquipamento_idOrderByIdDesc(manutencaoEquipamento.getEquipamento().getId());
		if (item != null) {
			item.setStatus(false);
			manutencaoEquipamentoRepository.save(item);
		}
		manutencaoEquipamento.setStatus(true);
		manutencaoEquipamento.setUsuarioCadastro(SessionUsuario.getInstance().getUsuario());
		manutencaoEquipamentoRepository.save(manutencaoEquipamento);
	}

	@Transactional(readOnly = false)
	public void alterar(ManutencaoEquipamento manutencaoEquipamento) {
		manutencaoEquipamento.setTecnico(SessionUsuario.getInstance().getUsuario());
		manutencaoEquipamento.setDataUltimaManutencao(new Date());
		manutencaoEquipamento.setDataPreventiva(dataPreventiva());
		manutencaoEquipamentoRepository.save(manutencaoEquipamento);
	}

	public Date dataPreventiva() {
		Calendar dataAtual = Calendar.getInstance();
		dataAtual.set(Calendar.MONTH, dataAtual.get(Calendar.MONTH) + 6);
		return dataAtual.getTime();
	}

	public Date dataFinalPrioridade() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		return calendar.getTime();
	}
}

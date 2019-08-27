package br.com.portalCrc.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.portalCrc.entity.Permissao;
import br.com.portalCrc.repository.PermissaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;


	@Transactional(readOnly = false)
	public void salvarOuEditar(Permissao permissao) {
		permissaoRepository.save(permissao);
	}

	public Collection<Permissao> lista() {
		return permissaoRepository.findAll();
	}

	public Permissao buscaPorId(Long id) {
		return permissaoRepository.findOne(id);
	}
}

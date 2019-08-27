package br.com.portalCrc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.portalCrc.entity.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
            
	//Usuario findByNome(String nome);
	Usuario findByLogin(String login);
	
	@Query("SELECT CASE WHEN COUNT(login) > 0 THEN true ELSE false END FROM Usuario u WHERE u.login = :login")
	boolean existeLogin(@Param("login") String login);
	
	
	List<Usuario> findByFuncionarioPessoaCpfAndFuncionario_unidadeAtual_id(String string, Long idUnidade);

	List<Usuario> findByFuncionarioPessoaNomeCompletoIgnoreCaseContainingAndFuncionario_unidadeAtual_id(String texto,
			Long idUnidade);
	
	
}

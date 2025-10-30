package br.com.TrueUnion.TrueUnion.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.TrueUnion.TrueUnion.Entities.LoginSessao;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryUsuario extends CrudRepository<Usuario, Integer> {

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);
	
	
	@Query("SELECT E FROM Usuario E WHERE E.inativo = false")
	Iterable<Usuario> findAllUsuarioAtivo();
	
	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByEmailAndSenha(String email, String senha);

	Optional<Usuario> findByCpf(String cpf);

}

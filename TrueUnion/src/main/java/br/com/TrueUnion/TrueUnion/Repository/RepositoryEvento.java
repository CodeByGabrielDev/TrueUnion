package br.com.TrueUnion.TrueUnion.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;

public interface RepositoryEvento extends CrudRepository<Evento, Integer> {

	@Query("SELECT E FROM Evento E WHERE E.donoEvento = :usuario")
	Iterable<Evento> findEventoByDono(Usuario usuario);
	
	
	
	
}

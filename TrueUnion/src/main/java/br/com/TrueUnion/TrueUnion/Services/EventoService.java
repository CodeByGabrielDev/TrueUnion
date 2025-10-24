package br.com.TrueUnion.TrueUnion.Services;

import java.lang.StackWalker.Option;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.Controller.UsuarioController;
import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.EventoCreateRequest;
import br.com.TrueUnion.TrueUnion.Entities.ConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryConvidadosEmEventos;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryStatusRSVP;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.EventoResponse;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;

@Service
public class EventoService {
	@Autowired
	RepositoryEvento event;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RepositoryUsuario usuarioRepository;
	@Autowired
	LoginService login;
	@Autowired
	RepositoryLoginSessao loginRepository;
	@Autowired
	RepositoryStatusRSVP statusRepository;
	@Autowired
	RepositoryConvidadosEmEventos convidadosEmEventos;

	@Transactional
	public EventoResponse createEvento(Usuario usuarioLogado, EventoCreateRequest evento) {
		if (evento.getDataFim().isBefore(evento.getDataInicio())) {
			throw new IllegalArgumentException("A data de fim nao pode ser anterior a data de inicio");
		} else {
			Evento eventoEntity = new Evento(evento.getNome(), evento.getOrcamento(), evento.getLocal(),
					evento.getDataInicio(), evento.getDataFim(), evento.getDescricao());

			eventoEntity.setDonoEvento(usuarioLogado);
			this.event.save(eventoEntity);
			return new EventoResponse(eventoEntity.getId(), eventoEntity.getNome(), eventoEntity.getOrcamentoEvento(),
					eventoEntity.getLocal(), eventoEntity.getDataInicio(), eventoEntity.getDataFim(),
					eventoEntity.getDescricao());

		}

	}

	public void convidar(String email, Evento eventoPegoPeloFront) {
		Usuario usuarioCapturadoViaEmail = this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Usuario inexistente, valide o email!"));
		System.out
				.println("encontrou" + usuarioCapturadoViaEmail.getNome() + " " + usuarioCapturadoViaEmail.isInativo());
		if (!usuarioCapturadoViaEmail.isInativo()) {
			ConvidadosEmEventos convidados = new ConvidadosEmEventos(usuarioCapturadoViaEmail, eventoPegoPeloFront);
			convidados.setStatus(this.statusRepository.findById(1).orElseThrow());
			this.convidadosEmEventos.save(convidados);
		} else {
			throw new RuntimeException("error");
		}

	}

	@Transactional
	public void updateEvento(int id, Evento eventoNovo) {

		// IMPLEMENTAR REGRA QUE PUXA OS EVENTOS VINCULADOS AO DONO DO TOKEN ATUAL E
		// SOLICITA PARA O MESMO QUAL EVENTO DESEJA ALTERAR DAQUELES QUE ELE POSSUI
		// DISPONIVEL QUE ELE CRIOU

	}

	public Iterable<Evento> listarEventosVinculadoAoTokenDaPagina(Usuario usuario) {

		Iterable<Evento> eventosVinculadoAoUsuario = this.event.findEventoByDono(usuario);

		if (eventosVinculadoAoUsuario == null) {
			throw new RuntimeException(
					"Erro, o usuario não possui eventos vinculados a ele para realização de convite, crie um evento!");

		}
		return eventosVinculadoAoUsuario;
	}

	public void validarConvitesEnviadosParaMim(Usuario usuario) {

		// VALIDAR SE É POSSIVEL PUXAR APENAS OS EVENTOS QUE ESTAO COM O STATUS_RSVP
		// COMO 1 , OU SEJA, PENDENTE, NAO TEM PORQUE EU PUXAR TODOS ATE AQUEELS QUE JA
		// FORAM ACEITSO!

	}

}

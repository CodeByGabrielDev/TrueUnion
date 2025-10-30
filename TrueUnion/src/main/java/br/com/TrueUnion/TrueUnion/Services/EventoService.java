package br.com.TrueUnion.TrueUnion.Services;

import java.lang.StackWalker.Option;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

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

	@Transactional
	public void convidar(String email, Evento eventoPegoPeloFront) {

		Usuario usuarioCapturadoViaEmail = this.usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Usuário inexistente, valide o email!"));

		// Verifica se o usuário já foi convidado para ESTE MESMO evento
		ConvidadosEmEventos conviteExistente = this.convidadosEmEventos
				.puxarLinhaConvidadosEmEventos(eventoPegoPeloFront, usuarioCapturadoViaEmail);

		if (conviteExistente != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já foi convidado para este evento.");
		}

		// Verifica se o usuário está inativo
		if (usuarioCapturadoViaEmail.isInativo()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível convidar um usuário inativo.");
		}

		// Cria o convite
		ConvidadosEmEventos novoConvite = new ConvidadosEmEventos(usuarioCapturadoViaEmail, eventoPegoPeloFront);
		novoConvite.setStatus(this.statusRepository.findById(1).orElseThrow()); // status: pendente
		this.convidadosEmEventos.save(novoConvite);
	}

	@Transactional
	public void updateEvento(Iterable<Evento> eventosVinculadosAoToken, String nomeEventoQueDesejaAlterar,
			EventoCreateRequest eventoAlterado) {
		
		
		
		
		for (Evento e : eventosVinculadosAoToken) {
			if (nomeEventoQueDesejaAlterar.equalsIgnoreCase(e.getNome())) {
				if (eventoAlterado.getNome() != null && !eventoAlterado.getNome().isBlank()) {
					e.setNome(eventoAlterado.getNome());
					System.out.println("alterou o nome aqui");
				}
				if (eventoAlterado.getOrcamento() != null) {
					e.setOrcamentoEvento(eventoAlterado.getOrcamento());
				}
				if (eventoAlterado.getLocal() != null && !eventoAlterado.getLocal().isBlank()) {
					e.setLocal(eventoAlterado.getLocal());
				}
				if (eventoAlterado.getDataInicio() != null && eventoAlterado.getDataFim() != null) {
					if (eventoAlterado.getDataInicio().isBefore(eventoAlterado.getDataFim())) {
						e.setDataInicio(eventoAlterado.getDataInicio());
						e.setDataFim(eventoAlterado.getDataFim());
					} else {
						throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
					}
				} else if (eventoAlterado.getDataInicio() != null && eventoAlterado.getDataFim() == null) {
					if (eventoAlterado.getDataInicio().isBefore(e.getDataFim())) {
						e.setDataInicio(eventoAlterado.getDataInicio());
					} else {
						throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
					}
				} else if (eventoAlterado.getDataFim() != null && eventoAlterado.getDataInicio() == null) {
					if (eventoAlterado.getDataFim().isAfter(e.getDataInicio())) {
						e.setDataFim(eventoAlterado.getDataFim());
					} else {
						throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
					}
				}
				this.event.save(e);
				break;
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	public Iterable<Evento> listarEventosVinculadoAoTokenDaPagina(Usuario usuario) {

		Iterable<Evento> eventosVinculadoAoUsuario = this.event.findEventoByDono(usuario);

		if (eventosVinculadoAoUsuario == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		}
		return eventosVinculadoAoUsuario;
	}

	public List<EventoResponse> validarConvitesEnviadosParaMim(Usuario usuario) {

		Iterable<Evento> listaDeEventosQueOUsuarioEstaConvidado = this.convidadosEmEventos
				.eventosDisponiveisParaVerificar(usuario);
		List<EventoResponse> listaDeEventosParaJogarNaTela = new ArrayList<>();
		for (Evento evento : listaDeEventosQueOUsuarioEstaConvidado) {
			EventoResponse respostaEmTela = new EventoResponse(evento.getId(), evento.getNome(),
					evento.getOrcamentoEvento(), evento.getLocal(), evento.getDataInicio(), evento.getDataFim(),
					evento.getDescricao());
			listaDeEventosParaJogarNaTela.add(respostaEmTela);
		}
		return listaDeEventosParaJogarNaTela;
	}

	@Transactional
	public void responderConvitePendente(Usuario usuario, String nomeEvento, String resposta) {

		Iterable<Evento> eventosPendentesDoUsuarioAtual = this.convidadosEmEventos
				.eventosDisponiveisParaVerificar(usuario);

		for (Evento e : eventosPendentesDoUsuarioAtual) {
			if (nomeEvento.equalsIgnoreCase(e.getNome())) {
				ConvidadosEmEventos convidadosEmEventos = this.convidadosEmEventos.puxarLinhaConvidadosEmEventos(e,
						usuario);
				if (resposta.equalsIgnoreCase("ACEITAR")) {
					convidadosEmEventos.setStatus(this.statusRepository.findById(3).orElseThrow());
					System.out.println("ENCONTROU");
					this.convidadosEmEventos.save(convidadosEmEventos);
					break;
				}
				if (resposta.equalsIgnoreCase("RECUSAR")) {
					convidadosEmEventos.setStatus(this.statusRepository.findById(2).orElseThrow());
					this.convidadosEmEventos.save(convidadosEmEventos);
					break;
				}

			}

		}

	}

}

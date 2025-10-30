package br.com.TrueUnion.TrueUnion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.TarefaRequest;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryEvento;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryTarefa;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.TarefaResponse;
import br.com.TrueUnion.TrueUnion.Services.TarefaService;

@RestController
@RequestMapping("/Tarefas")
public class TarefaController {

	@Autowired
	RepositoryUsuario usuarioRepo;
	@Autowired
	RepositoryLoginSessao loginSessao;
	@Autowired
	RepositoryEvento evento;
	@Autowired
	TarefaService tarefaService;

	@PostMapping("/CriarTarefas")
	public ResponseEntity<TarefaResponse> criarUmaTask(@RequestParam String nomeEvento, @RequestBody TarefaRequest task,
			@RequestHeader("Authorization") String header) {

		String tokenUsuario = header.replace("Bearer ", "").trim();

		Usuario usuarioEncontrado = this.loginSessao.findUsuarioByToken(tokenUsuario)
				.orElseThrow(() -> new RuntimeException("Não foi encontrado usuario com esse nome"));

		Iterable<Evento> eventosDoUsuario = this.evento.findEventoByDono(usuarioEncontrado);

		return ResponseEntity.ok(this.tarefaService.criarTask(task, nomeEvento, eventosDoUsuario));
	}

	@GetMapping("/ListarTarefas")
	public List<TarefaResponse> listarTarefasDisponiveisPorEvento(@RequestHeader("Authorization") String header) {
		String token = header.replace("Bearer ", "").trim();

		Usuario usuario = this.loginSessao.findUsuarioByToken(token)
				.orElseThrow(() -> new RuntimeException("Token nao encontrado!"));

		Iterable<Evento> listaDeEventos = this.evento.findEventoByDono(usuario);

		return this.tarefaService.listarTarefasPorEvento(listaDeEventos);
	}
}

package br.com.TrueUnion.TrueUnion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.EventoCreateRequest;
import br.com.TrueUnion.TrueUnion.Entities.Evento;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.EventoResponse;
import br.com.TrueUnion.TrueUnion.Services.EventoService;
import br.com.TrueUnion.TrueUnion.Services.LoginService;
import br.com.TrueUnion.TrueUnion.Utils.Utils;

@RestController
@RequestMapping("/Eventos")
public class EventoController {
	@Autowired
	EventoService eventoService;
	@Autowired
	LoginService loginService;

	@PostMapping("/CriarEventos")
	public ResponseEntity<EventoResponse> criarEvento(@RequestHeader("Authorization") String header,
			@RequestBody EventoCreateRequest eventoRequest) {
		// Extrai o token do header
		String token = header.replace("Bearer ", "").trim();

		// Busca o usuário dono do token
		Usuario usuarioLogado = loginService.getUsuarioByToken(token);

		// Chama o service passando o usuário logado
		EventoResponse response = eventoService.createEvento(usuarioLogado, eventoRequest);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/ConvidarUsuarios")
	public ResponseEntity<EventoResponse> convidarUsuariosParaEvento(@RequestHeader("Authorization") String header,
			@RequestParam String email, @RequestParam String nomeEvento) {

		String tokenUsuario = header.replace("Bearer ", "").trim();

		Usuario usuarioDaSessaoAtual = this.loginService.getUsuarioByToken(tokenUsuario);

		Iterable<Evento> eventosVinculadosAoUsuarioAtual = this.eventoService
				.listarEventosVinculadoAoTokenDaPagina(usuarioDaSessaoAtual);
		Evento eventoValidadoViaNome = Utils.encontrarEventoComBaseNoNome(nomeEvento, eventosVinculadosAoUsuarioAtual);
		EventoResponse respostaEmTela = new EventoResponse(eventoValidadoViaNome.getId(),
				eventoValidadoViaNome.getNome(), eventoValidadoViaNome.getOrcamentoEvento(),
				eventoValidadoViaNome.getLocal(), eventoValidadoViaNome.getDataInicio(),
				eventoValidadoViaNome.getDataFim(), eventoValidadoViaNome.getDescricao());
		this.eventoService.convidar(email, eventoValidadoViaNome);
		return ResponseEntity.ok(respostaEmTela);
	}

	@GetMapping("/ConvitesRecebidos")
	public List<EventoResponse> convitesRecebidos(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);

		List<EventoResponse> retornoNaTela = this.eventoService.validarConvitesEnviadosParaMim(usuarioPegoPeloToken);
		return retornoNaTela;
	}

	@PutMapping("/ResponderEvento")
	public void responderConvite(@RequestHeader("Authorization") String header, @RequestParam String nomeEvento,
			@RequestParam String resposta) {
		String token = header.replace("Bearer ", "").trim();

		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);
		this.eventoService.responderConvitePendente(usuarioPegoPeloToken, nomeEvento, resposta);
	}

	@PutMapping("/AtualizarEvento")
	public void atualizarEvento(@RequestHeader("Authorization") String header, @RequestBody EventoCreateRequest evento,
			@RequestParam String nomeEventoQueDesejaAlterar) {
		String token = header.replace("Bearer ", "").trim();
		Usuario usuarioPegoPeloToken = this.loginService.getUsuarioByToken(token);
		Iterable<Evento> eventos = this.eventoService.listarEventosVinculadoAoTokenDaPagina(usuarioPegoPeloToken);

		this.eventoService.updateEvento(eventos, nomeEventoQueDesejaAlterar, evento);
	}

}

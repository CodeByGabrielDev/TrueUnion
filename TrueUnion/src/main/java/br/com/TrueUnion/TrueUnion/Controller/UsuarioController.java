package br.com.TrueUnion.TrueUnion.Controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.TrueUnion.TrueUnion.Entities.LoginSessao;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Services.UsuarioService;

@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	RepositoryLoginSessao loginRepository;

	@GetMapping("/BuscarUsuario/{id}")
	public UsuarioResponseGeneric buscarUsuarioPorId(@PathVariable("id") int id) {
		return this.usuarioService.buscarUsuarioPorId(id);
	}

	@GetMapping("/BuscarTodosUsuariosDisponiveis")
	public List<UsuarioResponseGeneric> buscarTodosUsuariosDisponiveis() {
		return this.usuarioService.listarUsuariosDisponiveis();
	}

	@PutMapping("/ResetPassword")
	public ResponseEntity<UsuarioResponseGeneric> resetPassword(@RequestHeader("Authorization") String header,
			String senha) {
		String tokenRetiradoDoHeader = header.replace("Bearer ", "").trim();

		LoginSessao log = this.loginRepository.findByToken(tokenRetiradoDoHeader)
				.orElseThrow(() -> new RuntimeException("erro ao coletar o token"));

		Usuario usuarioEncontradoPeloToken = this.loginRepository.findUsuarioByToken(tokenRetiradoDoHeader)
				.orElseThrow(() -> new IllegalArgumentException("Erro"));

		return ResponseEntity.ok(this.usuarioService.resetPassword(senha, usuarioEncontradoPeloToken));

	}

}

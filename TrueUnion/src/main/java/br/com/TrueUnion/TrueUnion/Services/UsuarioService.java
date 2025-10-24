package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.UsuarioRequest;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	RepositoryUsuario usuarioRepository;

	@Autowired
	RepositoryLoginSessao loginRepository;

	@Transactional
	public UsuarioResponseGeneric createUsuario(UsuarioRequest usu) {
		if (usu == null) {
			throw new IllegalArgumentException("Erro: o objeto de usuário está nulo.");
		}

		// Validação de senha — deve conter letras e números
		boolean temLetra = false;
		boolean temNumero = false;

		for (char c : usu.getSenha().toCharArray()) {
			if (Character.isLetter(c)) {
				temLetra = true;
			} else if (Character.isDigit(c)) {
				temNumero = true;
			}
		}

		if (!temLetra || !temNumero) {
			throw new IllegalArgumentException("A senha deve conter letras e números.");
		}

		// Validação de CPF
		if (!Utils.cpfValido(usu.getCpf())) {
			throw new IllegalArgumentException("CPF inválido. Valide os campos e tente novamente.");
		}

		// Criação do usuário
		Usuario usuario = new Usuario();
		usuario.setNome(usu.getNome());
		usuario.setCpf(usu.getCpf());
		usuario.setEmail(usu.getEmail());
		usuario.setSenha(usu.getSenha());
		usuario.setTelefone(usu.getTelefone());
		usuario.setDataCadastramento(LocalDate.now());
		usuario.setInativo(false);

		this.usuarioRepository.save(usuario);

		return new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(), usuario.getEmail());
	}

	@Transactional
	public void updateUsuario(UsuarioRequest usuario) {

	}

	@Transactional
	public boolean deleteUsuario(int id) {
		Usuario usuarioBuscado = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado no banco"));
		this.usuarioRepository.deleteById(id);
		return true;
	}

	public UsuarioResponseGeneric buscarUsuarioPorId(int id) {
		Usuario usuario = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("OBJETO USUARIO INEXISTENTE COM ESSE ID"));
		UsuarioResponseGeneric usuarioResponse = new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(),
				usuario.getEmail());
		return usuarioResponse;

	}

	@Transactional
	public boolean inativarConta(Usuario usuarioPeloFront, String senha, String tokenUsuario) {
		if (usuarioPeloFront.getSenha().equals(senha)) {
			usuarioPeloFront.setInativo(true);
			this.loginRepository.deleteByToken(tokenUsuario);
			this.usuarioRepository.save(usuarioPeloFront);
			return true;
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Erro, senha incorreta!");
		}

	}

	@Transactional
	public UsuarioResponseGeneric resetPassword(String senha, String confirmarSenha, Usuario usuario) {
		Usuario testeSenha = this.usuarioRepository.findById(usuario.getId()).orElseThrow();
		if (testeSenha.getSenha().equalsIgnoreCase(usuario.getSenha())) {
			boolean temLetra = false;
			boolean temNumero = false;

			for (char c : senha.toCharArray()) {
				if (Character.isLetter(c)) {
					temLetra = true;
				} else if (Character.isDigit(c)) {
					temNumero = true;
				}
			}

			if (!temLetra || !temNumero) {
				throw new IllegalArgumentException("A senha deve conter letras e números");
			} else {
				Usuario usuarioNoBanco = this.usuarioRepository.findById(usuario.getId())
						.orElseThrow(() -> new IllegalArgumentException("Objeto inexistente"));

				usuarioNoBanco.setSenha(senha);
				UsuarioResponseGeneric respostaNoPut = new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(),
						usuario.getEmail());
				this.usuarioRepository.save(usuarioNoBanco);
				return respostaNoPut;
			}

		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta!");
		}

	}

	public List<UsuarioResponseGeneric> listarUsuariosDisponiveis() {
		Iterable<Usuario> usuariosEncontrados = this.usuarioRepository.findAllUsuarioAtivo();
		List<UsuarioResponseGeneric> usuariosResponseLista = new ArrayList<>();
		for (Usuario usu : usuariosEncontrados) {
			UsuarioResponseGeneric usuarioGeneric = new UsuarioResponseGeneric(usu.getId(), usu.getNome(),
					usu.getEmail());
			usuariosResponseLista.add(usuarioGeneric);
		}
		return usuariosResponseLista;
	}

}

package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.DTOs.RequestDTOs.UsuarioRequest;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import br.com.TrueUnion.TrueUnion.ResponseDTOs.UsuarioResponseGeneric;
import br.com.TrueUnion.TrueUnion.Utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	RepositoryUsuario usuarioRepository;

	@Transactional
	public UsuarioResponseGeneric createUsuario(UsuarioRequest usu) {

		if (usu != null) {
			if (Utils.cpfValido(usu.getCpf())) {
				Usuario usuario = new Usuario();
				usuario.setNome(usu.getNome());
				usuario.setCpf(usu.getCpf());
				usuario.setEmail(usu.getEmail());
				usuario.setSenha(usu.getSenha());
				usuario.setTelefone(usu.getTelefone());
				usuario.setDataCadastramento(LocalDate.now());
				usuario.setInativo(false);
				this.usuarioRepository.save(usuario);
				UsuarioResponseGeneric usuarioResponse = new UsuarioResponseGeneric(usuario.getId(), usuario.getNome(),
						usuario.getEmail());
				return usuarioResponse;

			} else {
				throw new IllegalArgumentException("Erro, validar, valide os campos se estão corretos");
			}
		} else {
			throw new IllegalArgumentException("Erro, objeto nulo.");
		}

	}

	@Transactional
	public void updateUsuario(String parametroAlterar, String result, int id) {

		try {
			Optional<Usuario> usuarioById = this.usuarioRepository.findById(id);

			if (usuarioById.isEmpty()) {
				throw new IllegalArgumentException("Erro, nao foi encontrado Usuario com esse id");
			} else {
				if (parametroAlterar.equalsIgnoreCase("nome")) {
					Usuario usu = usuarioById.get();
					usu.setNome(result);
				}
				if (parametroAlterar.equalsIgnoreCase("email")) {
					Usuario usu = usuarioById.get();
					usu.setEmail(result);
				}
				if (parametroAlterar.equalsIgnoreCase("telefone")) {
					Usuario usu = usuarioById.get();
					usu.setTelefone(result);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao atualizar o usuario. " + e.getMessage());
		}
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
	public UsuarioResponseGeneric resetPassword(String senha, Usuario usuario) {// ACHAR ALGUMA MANEIRA DE PEGAR O TOKEN
																				// DO USUARIO
		// ATUAL PARA REALIZAR O RESET, POSSIVELMENTE CHEGAR
		// NO MESMO METODO DE CRIAR EVENTO
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

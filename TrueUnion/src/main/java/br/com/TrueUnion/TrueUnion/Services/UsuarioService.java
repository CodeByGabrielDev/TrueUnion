package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.Configuration.HashPassword;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Enums.StatusUsuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryPerfilUsuario;
import br.com.TrueUnion.TrueUnion.Repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	RepositoryPerfilUsuario perfilUsuario;

	@Autowired
	HashPassword hash;

	public Usuario createUsuario(Usuario usu) {

		try {
			if (usu != null) {
				usu.setDataCadastramento(LocalDate.now());
				usu.setStatus(StatusUsuario.ATIVO);
				usu.setPerfil(this.perfilUsuario.findById(1).get());
				usu.setSenha(hash.passwordEncoder().encode(usu.getSenha()));
				this.usuarioRepository.save(usu); // montei uma tabela no banco de perfil de USUARIO, é mais flexivel,
													// tem dois inserts naquela tabela, id 1 sendo ADMINISTRADOR e id 2
													// sendo USUARIO(padrao)
				return usu;
			} else {
				throw new IllegalArgumentException("Erro, objeto nulo.");
			}

		} catch (Exception e) {
			throw new IllegalArgumentException("Erro ao criar usuario " + e.getMessage());
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
	public boolean inativarUsuario(int id) {
		try {
			Optional<Usuario> usuario = this.usuarioRepository.findById(id);
			if (!usuario.isEmpty()) {
				Usuario usuarioObject = usuario.get();
				usuarioObject.setStatus(StatusUsuario.INATIVO);
				return true;
			} else {
				throw new IllegalArgumentException("Erro, o objeto é inexistente ");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao executar a transacao " + e.getMessage());
		}
	}

	@Transactional
	public boolean deleteUsuario(int id) {
		try {
			Optional<Usuario> usuarioById = this.usuarioRepository.findById(id);
			if (!usuarioById.isEmpty()) {
				Usuario usuario = usuarioById.get();
				this.usuarioRepository.delete(usuario);
				return true;
			} else {
				throw new RuntimeException("Erro ao executar o delete, objeto nao encontrado");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao deletar o usuario" + e.getMessage());
		}
	}

	public Usuario buscarUsuarioPorId(int id) {
		try {
			Optional<Usuario> usuarioEncontrado = this.usuarioRepository.findById(id);
			if (!usuarioEncontrado.isEmpty()) {
				return usuarioEncontrado.get();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi encontrado Usuario com esse Id");
		}
	}

	@Transactional
	public Usuario resetPassword(String senha, Usuario usuario) {
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
			usuarioNoBanco.setSenha(this.hash.passwordEncoder().encode(senha));
			return this.usuarioRepository.save(usuarioNoBanco);
		}

	}

}

package br.com.TrueUnion.TrueUnion.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.TrueUnion.TrueUnion.Entities.LoginSessao;
import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryLoginSessao;
import br.com.TrueUnion.TrueUnion.Repository.RepositoryUsuario;
import jakarta.transaction.Transactional;

@Service
public class LoginService {

	private final RepositoryUsuario usuarioRepository;
	private final RepositoryLoginSessao loginSessaoRepository;

	public LoginService(RepositoryUsuario usuarioRepository, RepositoryLoginSessao loginSessaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.loginSessaoRepository = loginSessaoRepository;
	}

	@Transactional
	public String login(String email, String senha) {
		Usuario usuarioOpt = this.usuarioRepository.findByEmailAndSenha(email, senha)
				.orElseThrow(() -> new IllegalArgumentException("Erro, credenciais invalidas."));

		// Apaga login anterior (se existir)
		loginSessaoRepository.deleteByUsuario(usuarioOpt);

		String token = UUID.randomUUID().toString();
		LoginSessao sessao = new LoginSessao();
		sessao.setToken(token);
		sessao.setUsuario(usuarioOpt);
		sessao.setCriadoEm(LocalDateTime.now());
		loginSessaoRepository.save(sessao);

		return token;
	}

	public Usuario getUsuarioByToken(String token) {
		LoginSessao sessao = loginSessaoRepository.findByToken(token)
				.orElseThrow(() -> new RuntimeException("Token inválido"));

		return sessao.getUsuario();

	}

	public String getTokenByUsuarioId(Usuario usuario) {
		Usuario usuarioBuscado = this.usuarioRepository.findById(usuario.getId())
				.orElseThrow(() -> new IllegalArgumentException("Usuario nao encontrado"));
		return this.loginSessaoRepository.takeTokenByIdUserInTableLoginSessao(usuarioBuscado)
				.orElseThrow(() -> new IllegalArgumentException("usuario nao logado"));

	}
}

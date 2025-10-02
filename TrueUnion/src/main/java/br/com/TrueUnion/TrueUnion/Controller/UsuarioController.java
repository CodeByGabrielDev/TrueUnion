package br.com.TrueUnion.TrueUnion.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.TrueUnion.TrueUnion.Entities.Usuario;
import br.com.TrueUnion.TrueUnion.Services.UsuarioService;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/CriarUsuario")
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		return this.usuarioService.createUsuario(usuario);
	}

	@GetMapping("/BuscarUsuario/{id}")
	public Usuario buscarUsuarioPorId(@PathVariable int id) {
		return this.usuarioService.buscarUsuarioPorId(id);
	}

}

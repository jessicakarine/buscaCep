package br.com.buscaCep.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.buscaCep.model.Usuario;
import br.com.buscaCep.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity getUsuarioForCep(@RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.createUsuario(usuario));
	}
}

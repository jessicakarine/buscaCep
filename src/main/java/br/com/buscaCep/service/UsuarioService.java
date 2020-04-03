package br.com.buscaCep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.buscaCep.model.CEP;
import br.com.buscaCep.model.Usuario;
import br.com.buscaCep.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario createUsuario(Usuario usuario) {
		
		CEP cep = getBuscaCep(usuario.getCep());
		usuario.setLogradouro(cep.getLogradouro());
		usuario.setBairro(cep.getBairro());
		usuario.setUf(cep.getUf());
		return usuarioRepository.save(usuario);
	}

	public CEP getBuscaCep(String cep) {
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		
		return restTemplate.getForObject(url, CEP.class);
		//return Optional.ofNullable(usuarioRepository.getUsuarioForCep(cep));	//.orElseThrow(NotificationNotFoundException::new));
	}

}

package br.com.buscaCep.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.buscaCep.model.CEP;
import br.com.buscaCep.model.Usuario;
import br.com.buscaCep.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServiceTest {

	private static final String UF = "AP";
	private static final String NOME = "João";
	private static final String LOGRADOURO = "Avenida Eulálio Modesto, s/n";
	private static final Long ID = 1L;
	private static final String EMAIL = "joao@email.com";
	private static final String CEP = "68960970";
	private static final String BAIRRO = "Centro";

	private Usuario usuario;
	@MockBean
	UsuarioRepository usuarioRepository;
	@Autowired
	UsuarioService usuarioService;

	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
		usuario.setBairro(BAIRRO);
		usuario.setCep(CEP);
		usuario.setEmail(EMAIL);
		usuario.setId(ID);
		usuario.setLogradouro(LOGRADOURO);
		usuario.setNome(NOME);
		usuario.setUf(UF);
	}

	@Test
	public void createUsuario() {
		CEP cep = new CEP();
		cep.setBairro(BAIRRO);
		cep.setLogradouro(LOGRADOURO);
		cep.setUf(UF);
		BDDMockito.given(usuarioRepository.save(Mockito.any())).willReturn(usuario);
		Assert.assertEquals(usuario, usuarioService.createUsuario(usuario));
	}

	@Test
	public void TestgetNotificationByIdSucess() {
		BDDMockito.given(usuarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(usuario));
		Assert.assertEquals(Optional.of(usuario), usuarioService.getBuscaCep(CEP));
	}

}

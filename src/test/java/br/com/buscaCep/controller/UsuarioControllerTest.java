package br.com.buscaCep.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.com.buscaCep.model.Usuario;
import br.com.buscaCep.repository.UsuarioRepository;
import br.com.buscaCep.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

	private static final String $_UF = "$.uf";
	private static final String $_NOME = "$.nome";
	private static final String $_LOGRADOURO = "$.logradouro";
	private static final String $_EMAIL = "$.email";
	private static final String $_CEP = "$.cep";
	private static final String $_BAIRRO = "$.bairro";
	private static final String $_ID = "$.id";
	private static final String URL_FINAL = "/json/";
	private static final String URL_INICIAL = "https://viacep.com.br/ws/";
	private static final String UF = "AP";
	private static final String NOME = "João";
	private static final String LOGRADOURO = "Avenida Eulálio Modesto, s/n";
	private static final Long ID = 1L;
	private static final String EMAIL = "joao@email.com";
	private static final String CEP = "68960970";
	private static final String BAIRRO = "Centro";

	@Autowired
	private MockMvc mvc;

	@MockBean(name = "delete")
	private UsuarioService usuarioServiceTest;

	@MockBean
	UsuarioRepository usuarioRepository;

	private Usuario usuario;

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
	public void getUsuarioForCep() throws Exception {
		BDDMockito.given(usuarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(usuario));
		mvc.perform(get(URL_INICIAL + usuario.getCep() + URL_FINAL).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(jsonPath($_ID).value(usuario.getId()))
				.andExpect(jsonPath($_BAIRRO).value(usuario.getBairro()))
				.andExpect(jsonPath($_CEP).value(usuario.getCep()))
				.andExpect(jsonPath($_EMAIL).value(usuario.getEmail()))
				.andExpect(jsonPath($_LOGRADOURO).value(usuario.getLogradouro()))
				.andExpect(jsonPath($_NOME).value(usuario.getNome())).andExpect(jsonPath($_UF).value(usuario.getUf()));
	}

}

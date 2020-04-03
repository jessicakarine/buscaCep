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

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UsuarioService usuarioService;

	@MockBean(name = "delete")
	private UsuarioService usuarioServiceTest;

	@MockBean
	UsuarioRepository usuarioRepository;

	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
		usuario.setBairro("Centro");
		usuario.setCep("68960970");
		usuario.setEmail("joao@email.com");
		usuario.setId(1L);
		usuario.setLogradouro("Avenida Eulálio Modesto, s/n");
		usuario.setNome("João");
		usuario.setUf("AP");

	}

	@Test
	public void getUsuarioForCep() throws Exception {
		BDDMockito.given(usuarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(usuario));
		mvc.perform(get("https://viacep.com.br/ws/" + usuario.getCep() + "/json/")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(usuario.getId()))
				.andExpect(jsonPath("$.bairro").value(usuario.getBairro()))
				.andExpect(jsonPath("$.cep").value(usuario.getCep()))
				.andExpect(jsonPath("$.email").value(usuario.getEmail()))
				.andExpect(jsonPath("$.logradouro").value(usuario.getLogradouro()))
				.andExpect(jsonPath("$.nome").value(usuario.getNome()))
				.andExpect(jsonPath("$.uf").value(usuario.getUf()));
	}

}

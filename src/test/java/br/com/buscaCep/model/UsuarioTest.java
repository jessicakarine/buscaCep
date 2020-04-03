package br.com.buscaCep.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.buscaCep.endpoint.UsuarioController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

	private static final String UF = "AP";
	private static final String NOME = "João";
	private static final String LOGRADOURO = "Avenida Eulálio Modesto, s/n";
	private static final Long ID = 1L;
	private static final String EMAIL = "joao@email.com";
	private static final String CEP = "68960970";
	private static final String BAIRRO = "Centro";

	private Usuario usuario;

	@Mock
	private UsuarioController uControler;

	@Before
	public void Init() {
		MockitoAnnotations.initMocks(this);
		this.uControler = new UsuarioController();
	}

	@Test
	public void getBairro() {
		assertThat(usuario.getBairro(), is(equalTo(BAIRRO)));
	}

	@Test
	public void getCep() {
		assertThat(usuario.getCep(), is(equalTo(CEP)));
	}

	@Test
	public void getEmail() {
		assertThat(usuario.getEmail(), is(equalTo(EMAIL)));
	}

	@Test
	public void getId() {
		assertThat(usuario.getId(), is(equalTo(ID)));
	}

	@Test
	public void getLogradouro() {
		assertThat(usuario.getLogradouro(), is(equalTo(LOGRADOURO)));
	}

	@Test
	public void getNome() {
		assertThat(usuario.getNome(), is(equalTo(NOME)));
	}

	@Test
	public void getUf() {
		assertThat(usuario.getUf(), is(equalTo(UF)));
	}
}

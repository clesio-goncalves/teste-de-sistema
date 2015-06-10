package teste.usuario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteUsuarios {

	private FirefoxDriver driver;
	private PaginaLoginLogoutUsuario usuario;
	private PaginaUsuarios usuarios;
	private String nomeUsuarioLogado = "woshington valdeci de sousa";

	private String nomeNovoUsuario = "Clésio de Araújo";
	private String email = "clesio.pi@ifpi.com";
	private String senha = "12345678";
	private String confirmaSenha = senha;
	private boolean admin = true;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.usuario = new PaginaLoginLogoutUsuario(driver);
		this.usuarios = new PaginaUsuarios(driver);

		// Deve fazer login
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void u1deveFazerLogin() {
		// usuario.visita();
		// usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
		assertTrue(usuario.usuarioLogado(nomeUsuarioLogado));
	}

	@Test
	public void u2deveCadastrarUmUsuario() {
		usuarios.visita();
		usuarios.novo().cadastra(nomeNovoUsuario, email, senha, confirmaSenha,
				admin);
		assertTrue(usuarios.existeNaListagem(nomeNovoUsuario, email, admin));
	}

	@Test
	public void u3deveVerOsDetalhesDoUsuarioCadastrado() {
		// Ultimo usuário da lista
		usuarios.visita();
		PaginaDetalhesUsuario detalheUsuario = usuarios.detalhes();

		assertTrue(detalheUsuario.existeNaListagem(nomeNovoUsuario, email,
				admin));

	}

	@Test
	public void u4deveSairDoSistema() {
		usuario.logout();
		assertFalse(usuario.usuarioLogado(nomeUsuarioLogado));
	}

	@After
	public void finaliza() {
		this.driver.close();
	}

}

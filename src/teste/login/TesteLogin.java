package teste.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteLogin {

	private FirefoxDriver driver;
	private PaginaLoginLogoutUsuario usuario;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.usuario = new PaginaLoginLogoutUsuario(driver);
	}

	@Test
	public void l1emailSenhaEmBranco() {
		usuario.visita();
		usuario.login("", "");
		assertTrue(usuario.erroLogin());
	}

	@Test
	public void l2emailEmBranco() {
		usuario.visita();
		usuario.login("", "caraibas");
		assertTrue(usuario.erroLogin());
	}

	@Test
	public void l3senhaEmBranco() {
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "");
		assertTrue(usuario.erroLogin());
	}

	@Test
	public void l5emailSenhaIncorreta() {
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibassssssssssss");
		assertTrue(usuario.erroLogin());
	}

	@Test
	public void l6emailNaoCadastrado() {
		usuario.visita();
		usuario.login("woshingtonvaldeci22@gmail.com", "caraibas");
		assertTrue(usuario.erroLogin());
	}

	@Test
	public void l7loginEmailW() {
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
		assertFalse(usuario.erroLogin());
	}

	@Test
	public void l8logoutW() {
		l7loginEmailW();
		usuario.logout();
		assertTrue(usuario.saiuSistema());
	}

	@Test
	public void l9loginEmailC() {
		usuario.visita();
		usuario.login("clesio.pi@hotmail.com", "123456789");
		assertFalse(usuario.erroLogin());
	}

	@Test
	public void l9logoutC() {
		l9loginEmailC();
		usuario.logout();
		assertTrue(usuario.saiuSistema());
	}

	@After
	public void finaliza() {
		this.driver.close();
	}

}

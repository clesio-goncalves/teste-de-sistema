package teste.usuario;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.login.PaginaLoginLogoutUsuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteUsuarios {

	private FirefoxDriver driver;
	private PaginaLoginLogoutUsuario usuario;
	private PaginaUsuarios usuarios;
	private PaginaNovoUsuario novoUsuario;

	private String nome = "Clésio de Araújo Gonçalves";
	private String email = "clesio@ifpi.edu.br";
	private String senha = "123456789";
	private String confirmaSenha = senha;
	private boolean admin = false;

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.usuario = new PaginaLoginLogoutUsuario(driver);
		this.usuarios = new PaginaUsuarios(driver);
		this.novoUsuario = new PaginaNovoUsuario(driver);

		// Deve fazer login
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void u1tudoEmBranco() {
		usuarios.visita();
		usuarios.novo().cadastra("", "", "", "", false);
		assertTrue(novoUsuario.erroCadastroTudoEmBranco());
	}

	@Test
	public void u2nomeEmBranco() {
		usuarios.visita();
		usuarios.novo().cadastra("", email, senha, confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroCadastroNomeEmBranco());
	}

	@Test
	public void u3emailEmBranco() {
		usuarios.visita();
		usuarios.novo().cadastra(nome, "", senha, confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroCadastroEmailEmBranco());
	}

	@Test
	public void u4senhaEmBranco() {
		usuarios.visita();
		usuarios.novo().cadastra(nome, email, "", confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroCadastroSenhaEmBranco());
	}

	@Test
	public void u5confirmarSenhaEmBranco() {
		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, "", admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroCadastroConfirmarSenhaEmBranco());
	}

	@Test
	public void u6nomeMenorMinino() {

		String nome = "Clésio";

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, "", admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroNomeMenorMinimo());
	}

	@Test
	public void u7erroEmailInvalido() {

		String email = "clesio";

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, "", admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroEmailInvalido());
	}

	@Test
	public void u8erroSenhaMenorMinimo() {

		String senha = "123", confirmaSenha = "123";

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroSenhaMenorMinimo());
	}

	@Test
	public void u90erroSenhaMaiorMaximo() {

		String senha = "123456789012345", confirmaSenha = "123456789012345";

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroSenhaMaiorMaximo());
	}

	@Test
	public void u91erroConfirmarSenhaInvalida() {

		String confirmaSenha = "987654321";

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroConfirmarSenhaInvalida());
	}

	@Test
	public void u92erroEmailCadastrado() {

		String email = "clesio.pi@hotmail.com";

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, confirmaSenha, admin);
		assertTrue(novoUsuario.erroCadastro()
				&& novoUsuario.erroEmailCadastrado());
	}

	@Test
	public void u93dadosValidos() {
		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, confirmaSenha, admin);
		assertTrue(usuarios.usuarioSalvo());
	}

	@Test
	public void u94dadosValidosAdmin() {

		email = "clesio2@ifpi.edu.br";
		admin = true;

		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, confirmaSenha, admin);
		assertTrue(usuarios.usuarioSalvo());
	}

	@Test
	public void u95detalhesUsuario() {
		
		email = "clesio2@ifpi.edu.br";
		admin = true;

		// Ultimo usuário da lista
		usuarios.visita();
		PaginaDetalhesUsuario detalheUsuario = usuarios.detalhes();

		assertTrue(detalheUsuario.existeNaListagem(nome, email, admin));

	}

	@Test
	public void u96editarUsuario() {

		nome = "Clésio Gonçalves";
		email = "clesio.clesio@ifpi.edu.br";
		senha = "senhaeditada";
		confirmaSenha = senha;
		admin = false;

		// Ultimo usuario da lista
		usuarios.visita();
		usuarios.detalhes().editar()
				.preenche(nome, email, senha, confirmaSenha, admin);
		assertTrue(usuarios.usuarioSalvo());

	}

	@After
	public void finaliza() {
		this.driver.close();
	}

}

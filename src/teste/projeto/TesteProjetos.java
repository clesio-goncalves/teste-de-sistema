package teste.projeto;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.login.PaginaLoginLogoutUsuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteProjetos {

	private FirefoxDriver driver;
	private PaginaProjetos projetos;
	private PaginaLoginLogoutUsuario usuario;
	private PaginaNovoProjeto novoProjeto;

	private String titulo = "Projeto de TCC";
	private String descricao = "Desenvolvendo meu TCC";
	private String previsao = "30/08/16";

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.projetos = new PaginaProjetos(driver);
		this.usuario = new PaginaLoginLogoutUsuario(driver);
		this.novoProjeto = new PaginaNovoProjeto(driver);

		// Faz o login do usuário administrador
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void p1erroTudoEmBranco() {
		projetos.visita();
		projetos.novo().cadastra("", "", "");
		assertTrue(novoProjeto.erroCadastroTudoEmBranco());
	}

	@Test
	public void p2erroTituloEmBranco() {
		projetos.visita();
		projetos.novo().cadastra("", descricao, previsao);
		assertTrue(novoProjeto.erroCadastro()
				&& novoProjeto.erroTituloEmBranco());
	}

	@Test
	public void p3erroDescricaoEmBranco() {
		projetos.visita();
		projetos.novo().cadastra(titulo, "", previsao);
		assertTrue(novoProjeto.erroCadastro()
				&& novoProjeto.erroDescricaoEmBranco());
	}

	@Test
	public void p4erroTituloMenorMinimo() {
		projetos.visita();
		projetos.novo().cadastra("Projeto", descricao, previsao);
		assertTrue(novoProjeto.erroCadastro()
				&& novoProjeto.erroTituloMenorMinimo());
	}

	@Test
	public void p5erroDescricaoMenorMinimo() {
		projetos.visita();
		projetos.novo().cadastra(titulo, "Desenvolvendo", previsao);
		assertTrue(novoProjeto.erroCadastro()
				&& novoProjeto.erroDescricaoMenorMinimo());
	}

	@Test
	public void p6erroPrevisaoFormatoIncorreto() {
		projetos.visita();
		projetos.novo().cadastra(titulo, descricao, "300816");
		assertTrue(novoProjeto.erroCadastro()
				&& novoProjeto.erroPrevisaoFormatoIncorreto());
	}

	@Test
	public void p7dadosValidos() {
		projetos.visita();
		projetos.novo().cadastra(titulo, descricao, previsao);
		assertTrue(projetos.projetoSalvo());
	}

	@Test
	public void p8tituloCadastrado() {
		projetos.visita();
		projetos.novo().cadastra(titulo, descricao, previsao);
		assertTrue(novoProjeto.erroCadastro()
				&& novoProjeto.erroTituloCadastrado());
	}

	@Test
	public void p90detalhesProjeto() {

		// Ultimo projeto da lista
		projetos.visita();
		PaginaDetalhesProjeto detalheProjeto = projetos.detalhes(1);

		assertTrue(detalheProjeto
				.existeNosDetalhes(titulo, descricao, previsao));
	}

	@Test
	public void p91editarProjeto() {

		titulo = "Teste de projeto de TCC";
		descricao = "Desenvolver o projeto de TCC";
		previsao = "30/07/2015";

		// Ultimo projeto da lista
		projetos.visita();
		projetos.detalhes(1).editar().preenche(titulo, descricao, previsao);
		assertTrue(projetos.projetoSalvo());
	}

	@Test
	public void p92adicionarColaboradorAoProjeto() {

		// Ultimo projeto da lista
		projetos.visita();
		PaginaColaboradoresDoProjeto colaboradoresProjeto = projetos
				.colaboradores(1);

		// adiciona o último usuário da lista de colaboradores [Passando usuário
		// comum ou gerente]
		colaboradoresProjeto.membrosProjeto("comum");

		assertTrue(colaboradoresProjeto.colaboradorAdicionado());
	}

	@Test
	public void p93adicionarGerenteAoProjeto() {

		// Ultimo projeto da lista
		projetos.visita();
		PaginaColaboradoresDoProjeto colaboradoresProjeto = projetos
				.colaboradores(1);

		// adiciona o último usuário da lista de colaboradores [Passando usuário
		// comum ou gerente]
		colaboradoresProjeto.membrosProjeto("gerente");

		assertTrue(colaboradoresProjeto.colaboradorAdicionado());
	}

	@Test
	public void p94finalizarProjeto() {

		projetos.visita();

		// Finaliza o primeiro projeto
		projetos.finalizar(1);

		assertTrue(projetos.projetoSalvo());
	}

	@Test
	public void p95verificaProjetoFinalizado() {

		titulo = "Teste de projeto de TCC";
		descricao = "Desenvolver o projeto de TCC";
		previsao = "30/07/2015";

		PaginaProjetosFinalizados finalizados = new PaginaProjetosFinalizados(
				driver);

		// Visita a pagina de projetos finalizados
		finalizados.visita();
		assertTrue(finalizados.existeNaListagem(titulo, previsao));

	}

	@After
	public void finaliza() {
		this.driver.close();
	}

}

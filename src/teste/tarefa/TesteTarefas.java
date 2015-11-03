package teste.tarefa;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.login.PaginaLoginLogoutUsuario;
import teste.projeto.PaginaProjetos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteTarefas {

	private FirefoxDriver driver;
	private PaginaTarefas tarefas;
	private PaginaProjetos projetos;
	private PaginaLoginLogoutUsuario usuario;
	private PaginaNovaTarefa novaTarefa;

	private String titulo = "Desenvolver as ideias";
	private String descricao = "Escrita do capítulo 3 e implementação do módulo de exportação de dados";
	private String projeto = "Projeto de TCC2";

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.tarefas = new PaginaTarefas(driver);
		this.projetos = new PaginaProjetos(driver);
		this.usuario = new PaginaLoginLogoutUsuario(driver);
		this.novaTarefa = new PaginaNovaTarefa(driver);

		// Deve se logar como administrador
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void t1erroTudoEmBranco() {

		// Deve Cadastrar um projeto
		projetos.visita();
		projetos.novo().cadastra(projeto, "Desenvolvendo meu TCC", "30/08/16");

		// Adiciona uma tarefa
		projetos.tarefas(1).nova().cadastra("", "");
		assertTrue(novaTarefa.erroCadastroTudoEmBranco());
	}

	@Test
	public void t2erroTituloEmBranco() {
		projetos.tarefas(1).nova().cadastra("", descricao);
		assertTrue(novaTarefa.erroCadastro() && novaTarefa.erroTituloEmBranco());
	}

	@Test
	public void t3erroDescricaoEmBranco() {
		projetos.tarefas(1).nova().cadastra(titulo, "");
		assertTrue(novaTarefa.erroCadastro()
				&& novaTarefa.erroDescricaoEmBranco());
	}

	@Test
	public void t4erroTituloMenorMinimo() {
		projetos.tarefas(1).nova().cadastra("DES", descricao);
		assertTrue(novaTarefa.erroCadastro()
				&& novaTarefa.erroTituloMenorMinimo());
	}

	@Test
	public void t5erroDescricaoMenorMinimo() {
		projetos.tarefas(1).nova().cadastra(titulo, "Escrita");
		assertTrue(novaTarefa.erroCadastro()
				&& novaTarefa.erroDescricaoMenorMinimo());
	}

	@Test
	public void t6dadosValidos() {
		projetos.tarefas(1).nova().cadastra(titulo, descricao);
		assertTrue(tarefas.tarefaSalva());
	}

	@Test
	public void t7detalhesTarefa() {
		PaginaDetalhesTarefa detalheTarefa = projetos.tarefas(1).detalhes();
		assertTrue(detalheTarefa.existeNosDetalhes(titulo, descricao));
	}

	@Test
	public void t8editarTarefa() {
		titulo = "Formatação do Texto";
		descricao = "Formatar o documento segundo as normas da ABNT";

		projetos.tarefas(1).detalhes().editar()
				.preenche(titulo, descricao, projeto);

		assertTrue(tarefas.tarefaSalva());
	}

	@Test
	public void t9finalizarTarefa() {
		projetos.tarefas(1).finalizar();
		assertTrue(tarefas.tarefaSalva());
	}

	@After
	public void finaliza() {
		driver.close();
	}

}

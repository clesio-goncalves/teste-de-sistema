package teste.acao;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.login.PaginaLoginLogoutUsuario;
import teste.projeto.PaginaProjetos;
import teste.tarefa.PaginaTarefas;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteAcoes {

	private FirefoxDriver driver;
	private PaginaProjetos projetos;
	private PaginaLoginLogoutUsuario usuario;
	private PaginaAcoes acoes;
	private PaginaNovaAcao novaAcao;
	private PaginaTarefas tarefas;

	private String titulo = "Desenvolver as ideias";
	private String descricao = "Escrita do capítulo 3 e implementação do módulo de exportação de dados";

	private String tipo = "Desenvolvimento";
	private String observacao = "Desenvolvendo a metodologia da pesquisa";

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.projetos = new PaginaProjetos(driver);
		this.usuario = new PaginaLoginLogoutUsuario(driver);
		this.acoes = new PaginaAcoes(driver);
		this.novaAcao = new PaginaNovaAcao(driver);
		this.tarefas = new PaginaTarefas(driver);

		// Deve se logar como administrador
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void a1erroObservacaoEmBranco() {
		// cadastra a tarefa
		projetos.visita();
		projetos.tarefas(1).nova().cadastra(titulo, descricao);

		tarefas.detalhes().acao().cadastra(tipo, "");
		assertTrue(novaAcao.erroCadastro() && novaAcao.erroObservacaoEmBranco());
	}

	@Test
	public void a2dadosValidosAcao() {
		projetos.tarefas(1).detalhes().acao().cadastra(tipo, observacao);
		assertTrue(acoes.acaoSalva());
	}

	@Test
	public void a3detalheAcao() {
		acoes.visita();
		assertTrue(acoes.visao().existeNosDetalhes(observacao));
	}

	@Test
	public void a4editarAcao() {

		tipo = "Planejamento";
		observacao = "Escrita do capítulo 1 e 2";

		acoes.visita();
		acoes.editar().preenche(tipo, observacao);
		assertTrue(acoes.acaoSalva());
	}

	// @After
	public void finaliza() {
		driver.close();
	}

}

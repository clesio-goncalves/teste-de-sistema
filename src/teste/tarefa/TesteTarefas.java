package teste.tarefa;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.projeto.PaginaProjetos;
import teste.usuario.PaginaLoginLogoutUsuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteTarefas {

	private FirefoxDriver driver;
	private PaginaTarefas tarefas;
	private PaginaProjetos projetos;

	String titulo = "Desenvolver o TCC";
	String descricao = "Escrever os objetivos e justificativas";

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.tarefas = new PaginaTarefas(driver);
		this.projetos = new PaginaProjetos(driver);

		// Deve se logar como administrador
		PaginaLoginLogoutUsuario usuario = new PaginaLoginLogoutUsuario(driver);
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void t1deveAdicionarUmaTarefaAoProjeto() {
		// Deve Cadastrar um projeto
		projetos.visita();
		projetos.novo().cadastra("Projeto de TCC", "Desenvolvendo meu TCC",
				"17/6/2015");

		// Remover isso depois que woshington concertar a funcionalidade de
		// adicionar projeto
		projetos.visita();
		projetos.tarefas(1).nova().cadastra(titulo, descricao);
		assertTrue(tarefas.existeNaListagem(titulo, descricao));
	}

	@Test
	public void t2deveVerOsDetalhesDeUmaTarefa() {
		// Remover isso depois que woshington concertar a funcionalidade de
		// adicionar projeto
		projetos.visita();
		PaginaDetalhesTarefa detalheTarefa = projetos.tarefas(1).detalhes();
		assertTrue(detalheTarefa.existeNosDetalhes(titulo, descricao));
	}

	@Test
	public void t3deveFinalizarUmaTarefa() {
		// Remover isso depois que woshington concertar a funcionalidade de
		// adicionar projeto
		projetos.visita();
		projetos.tarefas(1).finalizar();
		assertFalse(tarefas.existeNaListagem(titulo, descricao));
	}

	@After
	public void finaliza() {
		driver.close();
	}

}

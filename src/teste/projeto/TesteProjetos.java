package teste.projeto;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.usuario.PaginaLoginLogoutUsuario;
import teste.usuario.PaginaUsuarios;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteProjetos {

	private FirefoxDriver driver;
	private PaginaProjetos projetos;
	private final String titulo = "Teste de engenharia12346";
	private final String descricao = "Desenvolvendo meu TCC";
	private final String previsao = "30-08-16";

	// Converte a previsão passada
	String[] conversor = previsao.split("-");
	private String visualizaPrevisao = conversor[0] + "/" + conversor[1] + "/"
			+ conversor[2];

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.projetos = new PaginaProjetos(driver);

		// Faz o login do usuário administrador
		PaginaLoginLogoutUsuario usuario = new PaginaLoginLogoutUsuario(driver);
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void p1deveCadastrarUmProjeto() {
		projetos.visita();
		projetos.novo().cadastra(titulo, descricao, previsao);
		assertTrue(projetos.existeNaListagem(titulo, visualizaPrevisao));
	}

	@Test
	public void p2deveVerOsDetalhesDoProjetoCadastrado() {
		// Ultimo projeto da lista
		PaginaDetalhesProjeto detalheProjeto = projetos.detalhes(1);
		assertTrue(detalheProjeto.existeNosDetalhes(titulo, descricao,
				visualizaPrevisao));
	}

	@Test
	public void p3deveAdicionarColaboradoresAoProjeto() {
		String nome = "Rafael Torres";
		String email = "rafael123456@ifpi.edu.br";
		String senha = "123456789";
		String repetirSenha = senha;
		boolean admin = false;

		// Adiciona outro usuário q será colaborador do projeto
		PaginaUsuarios usuarios = new PaginaUsuarios(driver);
		usuarios.visita();
		usuarios.novo().cadastra(nome, email, senha, repetirSenha, admin);

		// Ultimo projeto da lista
		projetos.visita();
		PaginaColaboradoresDoProjeto colaboradoresProjeto = projetos
				.colaboradores(1);

		// adiciona o último usuário da lista de colaboradores [Passando usuário
		// comum ou gerente]
		colaboradoresProjeto.membrosProjeto("comum");

		assertTrue(colaboradoresProjeto.existeEmMembrosDoProjeto(nome, email));
	}

	@Test
	public void p4deveFinalizarUmProjeto() {
		projetos.visita();

		// Finaliza o primeiro projeto
		PaginaProjetosFinalizados finalizados = projetos.finalizar(1);

		// Visita a pagina de projetos finalizados
		finalizados.visita();

		// Ver os detalhes do projeto finalizado
		PaginaDetalhesProjetoFinalizado proFinalizado = finalizados
				.detalhesFinalizados();

		// Verifica se os detalhes do projeto finalizado são os mesmo do projeto
		// q foi finalizado
		assertTrue(proFinalizado.existeNaListagem(titulo, descricao,
				visualizaPrevisao));
	}

	@After
	public void finaliza() {
		this.driver.close();
	}

}

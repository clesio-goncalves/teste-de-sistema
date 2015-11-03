package teste.notificacao;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.firefox.FirefoxDriver;

import teste.login.PaginaLoginLogoutUsuario;
import teste.projeto.PaginaColaboradoresDoProjeto;
import teste.projeto.PaginaProjetos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteNotificacao {

	private FirefoxDriver driver;
	private PaginaNotificacaoProjeto notificacao;
	private PaginaProjetos projetos;
	private PaginaLoginLogoutUsuario usuario;

	private String assunto = "TCC";
	private String texto = "TCC completo, conforme recomendações";

	private String caminho = "/home/clesio/Documentos/";
	private String arquivo = "resumo.odt";
	private String arquivoAnexo = caminho + arquivo;

	private String titulo = "Teste de Projeto 1";
	private String descricao = "Desenvolvendo meu Projeto 1";
	private String previsao = "30/08/16";

	@Before
	public void inicializa() {
		this.driver = new FirefoxDriver();
		this.notificacao = new PaginaNotificacaoProjeto(driver);
		this.projetos = new PaginaProjetos(driver);
		this.usuario = new PaginaLoginLogoutUsuario(driver);

		// Faz o login do usuário administrador
		usuario.visita();
		usuario.login("woshingtonvaldeci@gmail.com", "caraibas");
	}

	@Test
	public void n1erroTudoEmBranco() {

		// Deve Cadastrar um projeto 1
		projetos.visita();
		projetos.novo().cadastra(titulo, descricao, previsao);

		projetos.notificar(1).preenche("", "", "");
		assertTrue(notificacao.errotudoEmBranco());
	}

	@Test
	public void n2erroAssuntoEmBranco() {

		projetos.visita();
		projetos.notificar(1).preenche("", texto, arquivoAnexo);
		assertTrue(notificacao.erroNotificacao()
				&& notificacao.erroassuntoEmBranco());
	}

	@Test
	public void n3erroTextoEmBranco() {
		projetos.visita();
		projetos.notificar(1).preenche(assunto, "", arquivoAnexo);
		assertTrue(notificacao.erroNotificacao()
				&& notificacao.errotextoEmBranco());
	}

	@Test
	public void n4dadosValidosSemColaborador() {
		projetos.visita();
		projetos.notificar(1).preenche(assunto, texto, arquivoAnexo);
		assertTrue(notificacao.erronehumColaborador());
	}

	@Test
	public void n5dadosValidosComColaborador() {

		// Adiciona colaborador
		projetos.visita();
		PaginaColaboradoresDoProjeto colaboradoresProjeto = projetos
				.colaboradores(1);
		colaboradoresProjeto.membrosProjeto("comum");

		// Envia mensagem
		projetos.visita();
		projetos.notificar(1).preenche(assunto, texto, arquivoAnexo);
		assertTrue(notificacao.mensagemEnviada());
	}

	@Test
	public void n6verificaAnexo() {
		projetos.visita();
		assertTrue(notificacao.verificaArquivo(arquivo).exists());
	}

	@After
	public void finaliza() {
		this.driver.close();
	}

}

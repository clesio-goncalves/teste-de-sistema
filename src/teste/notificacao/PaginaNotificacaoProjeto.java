package teste.notificacao;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaNotificacaoProjeto {

	private WebDriver driver;

	public PaginaNotificacaoProjeto(WebDriver driver) {
		this.driver = driver;
	}

	public void preenche(String assunto, String texto, String anexo) {
		WebElement txtAssunto = driver.findElement(By.name("assunto"));
		WebElement txtTexto = driver.findElement(By.name("texto"));
		WebElement txtAnexo = driver.findElement(By.name("anexo"));

		txtAssunto.sendKeys(assunto);
		txtTexto.sendKeys(texto);
		txtAnexo.sendKeys(anexo);

		txtAssunto.submit();
	}

	public File verificaArquivo(String arquivo) {

		String[] url = driver.getCurrentUrl().split("/");
		int projeto = Integer.parseInt(url[url.length - 1]);

		File file = new File("/var/www/html/engenharia/webroot/email/projeto/"
				+ projeto + "/" + arquivo);

		return file;
	}

	public boolean erroNotificacao() {
		return driver.getPageSource().contains(
				"Ocorreu um problema ao enviar sua mensagem");
	}

	public boolean erroassuntoEmBranco() {
		return driver.getPageSource().contains("Error: Assunto obrigatório");
	}

	public boolean errotextoEmBranco() {
		return driver.getPageSource().contains("Error: Texto obrigatório");
	}

	public boolean erronehumColaborador() {
		return driver.getPageSource().contains(
				"Nenhum colaborador cadastrado para esse projeto");
	}

	public boolean mensagemEnviada() {
		return driver.getPageSource().contains(
				"Mensagem enviada, aguarde nosso retorno");
	}

	public boolean errotudoEmBranco() {
		return erroNotificacao() && erroassuntoEmBranco()
				&& errotextoEmBranco();
	}

}

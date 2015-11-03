package teste.projeto;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import teste.notificacao.PaginaNotificacaoProjeto;
import teste.tarefa.PaginaTarefas;

public class PaginaProjetos {

	private WebDriver driver;

	public PaginaProjetos(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost/engenharia/admin/projeto");
	}

	public PaginaNovoProjeto novo() {
		driver.findElement(By.linkText("Novo")).click();
		return new PaginaNovoProjeto(driver);
	}

	public boolean existeNaListagem(String titulo, String previsao) {
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(previsao);
	}

	public PaginaDetalhesProjeto detalhes(int posicao) {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Detalhes"));
		elementos.get(posicao - 1).click();

		return new PaginaDetalhesProjeto(driver);
	}

	public PaginaColaboradoresDoProjeto colaboradores(int posicao) {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Colaboradores"));
		elementos.get(posicao - 1).click();

		return new PaginaColaboradoresDoProjeto(driver);
	}

	public PaginaTarefas tarefas(int posicao) {
		List<WebElement> elementos = driver
				.findElements(By.linkText("Tarefas"));
		elementos.get(posicao - 1).click();

		return new PaginaTarefas(driver);
	}

	public PaginaNotificacaoProjeto notificar(int posicao) {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Notificar"));
		elementos.get(posicao - 1).click();

		return new PaginaNotificacaoProjeto(driver);
	}

	public PaginaProjetosFinalizados finalizar(int posicao) {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Finalizar"));
		elementos.get(posicao - 1).click();

		// Aceita o alert
		driver.switchTo().alert().accept();

		return new PaginaProjetosFinalizados(driver);
	}

	public boolean projetoSalvo() {
		return driver.getPageSource().contains("O projeto foi salvo");
	}
}
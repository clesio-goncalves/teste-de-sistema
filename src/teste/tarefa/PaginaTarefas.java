package teste.tarefa;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import teste.projeto.PaginaProjetos;

public class PaginaTarefas {

	private WebDriver driver;

	public PaginaTarefas(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		PaginaProjetos projetos = new PaginaProjetos(driver);

		// Remover isso depois que woshington concertar a funcionalidade de
		// adicionar projeto
		projetos.visita();

		projetos.tarefas();
	}

	public PaginaNovaTarefa nova() {
		driver.findElement(By.linkText("Add Tarefa")).click();
		return new PaginaNovaTarefa(driver);
	}

	public boolean existeNaListagem(String titulo, String descricao) {
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(descricao);
	}

	public void finalizar() {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Finalizar"));
		elementos.get(elementos.size() - 1).click();

		// Aceitar o meu alert
		driver.switchTo().alert().accept();
	}

	public PaginaDetalhesTarefa detalhes() {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Detalhes"));
		elementos.get(elementos.size() - 1).click();

		return new PaginaDetalhesTarefa(driver);
	}
}

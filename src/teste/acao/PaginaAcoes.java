package teste.acao;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import teste.projeto.PaginaProjetos;

public class PaginaAcoes {

	private WebDriver driver;
	private PaginaProjetos projetos;

	public PaginaAcoes(WebDriver driver) {
		this.driver = driver;
		this.projetos = new PaginaProjetos(this.driver);
	}

	public void visita() {
		projetos.tarefas(1).detalhes().acoes();
	}
	
	public PaginaDetalheAcao visao() {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Visão"));
		elementos.get(elementos.size() - 1).click();

		return new PaginaDetalheAcao(driver);
	}
	
	public PaginaEditarAcao editar() {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Editar"));
		elementos.get(elementos.size() - 1).click();

		return new PaginaEditarAcao(driver);
	}

	public boolean acaoSalva() {
		return driver.getPageSource().contains("A ação foi salva");
	}

}

package teste.projeto;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaProjetosFinalizados {

	private final WebDriver driver;

	public PaginaProjetosFinalizados(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost/engenharia/admin/projeto/finalizados");
	}

	public PaginaDetalhesProjetoFinalizado detalhesFinalizados() {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Detalhes"));
		elementos.get(elementos.size() - 1).click();

		return new PaginaDetalhesProjetoFinalizado(driver);
	}
}

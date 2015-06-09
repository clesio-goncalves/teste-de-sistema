package teste.projeto;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaColaboradoresDoProjeto {

	private final WebDriver driver;
	private String tipo;

	public PaginaColaboradoresDoProjeto(WebDriver driver) {
		this.driver = driver;
	}

	public void membrosProjeto(String tipo) {
		this.tipo = tipo;
		List<WebElement> elementos = driver.findElements(By.linkText(tipo
				.equals("gerente") ? "Gerente" : "Adicionar"));
		elementos.get(elementos.size() - 1).click();

		// Aceita o alert
		driver.switchTo().alert().accept();
	}

	public boolean existeEmMembrosDoProjeto(String nome, String email) {
		return driver.getPageSource().contains(nome)
				&& driver.getPageSource().contains(email)
				&& driver.getPageSource().contains(
						tipo.equals("gerente") ? "SIM" : "NAO");
	}
}

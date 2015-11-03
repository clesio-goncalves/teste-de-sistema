package teste.acao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PaginaEditarAcao {

	private final WebDriver driver;

	public PaginaEditarAcao(WebDriver driver) {
		this.driver = driver;
	}

	public void preenche(String tipo, String observacao) {
		Select cbTipo = new Select(driver.findElement(By.name("tipo")));
		cbTipo.selectByVisibleText(tipo);

		WebElement txtObservacao = driver.findElement(By.name("observacao"));
		
		txtObservacao.clear();
		txtObservacao.sendKeys(observacao);

		txtObservacao.submit();
	}

}

package teste.acao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PaginaNovaAcao {

	private final WebDriver driver;

	public PaginaNovaAcao(WebDriver driver) {
		this.driver = driver;
	}

	public void cadastra(String tipo, String observacao) {
		Select cbTipo = new Select(driver.findElement(By.name("tipo")));
		cbTipo.selectByVisibleText(tipo);

		WebElement txtObservacao = driver.findElement(By.name("observacao"));

		txtObservacao.sendKeys(observacao);

		txtObservacao.submit();
	}

	public boolean erroCadastro() {
		return driver.getPageSource().contains(
				"A ação não pode ser salva. Tente novamente");
	}

	public boolean erroObservacaoEmBranco() {
		return driver.getPageSource()
				.contains("Error: Observação obrigatório!");
	}

}

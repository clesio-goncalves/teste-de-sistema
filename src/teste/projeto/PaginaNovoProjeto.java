package teste.projeto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaNovoProjeto {

	private final WebDriver driver;

	public PaginaNovoProjeto(WebDriver driver) {
		this.driver = driver;
	}

	public void cadastra(String titulo, String descricao, String previsao) {
		WebElement txtTitulo = driver.findElement(By.name("titulo"));
		WebElement txtDescricao = driver.findElement(By.name("descricao"));
		WebElement txtPrevisao = driver.findElement(By.name("previsao"));

		txtTitulo.sendKeys(titulo);
		txtDescricao.sendKeys(descricao);
		txtPrevisao.sendKeys(previsao);

		txtTitulo.submit();
	}

}

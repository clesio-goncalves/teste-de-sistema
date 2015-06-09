package teste.tarefa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaNovaTarefa {

	private WebDriver driver;

	public PaginaNovaTarefa(WebDriver driver) {
		this.driver = driver;
	}

	public void cadastra(String titulo, String descricao) {
		WebElement txtTitulo = driver.findElement(By.name("titulo"));
		WebElement txtDescricao = driver.findElement(By.name("descricao"));

		txtTitulo.sendKeys(titulo);
		txtDescricao.sendKeys(descricao);

		txtTitulo.submit();
	}
}

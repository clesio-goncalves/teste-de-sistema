package teste.projeto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaEditarProjeto {

	private final WebDriver driver;

	public PaginaEditarProjeto(WebDriver driver) {
		this.driver = driver;
	}

	public void preenche(String novoTitulo, String novaDescricao,
			String novaPrevisao) {

		WebElement titulo = driver.findElement(By.name("titulo"));
		WebElement descricao = driver.findElement(By.name("descricao"));
		WebElement previsao = driver.findElement(By.name("previsao"));

		titulo.clear();
		titulo.sendKeys(novoTitulo);
		
		descricao.clear();
		descricao.sendKeys(novaDescricao);
		
		previsao.clear();
		previsao.sendKeys(novaPrevisao);

		titulo.submit();
	}

}

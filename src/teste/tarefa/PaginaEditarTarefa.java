package teste.tarefa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PaginaEditarTarefa {

	private final WebDriver driver;

	public PaginaEditarTarefa(WebDriver driver) {
		this.driver = driver;
	}

	public void preenche(String novoTituloTarefa, String novaDescricaoTarefa,
			String novoProjetoTarefa) {

		WebElement titulo = driver.findElement(By.name("titulo"));
		WebElement descricao = driver.findElement(By.name("descricao"));

		Select cbProjeto = new Select(driver.findElement(By.name("projeto_id")));
		cbProjeto.selectByVisibleText(novoProjetoTarefa);

		titulo.clear();
		titulo.sendKeys(novoTituloTarefa);

		descricao.clear();
		descricao.sendKeys(novaDescricaoTarefa);

		titulo.submit();
	}

}

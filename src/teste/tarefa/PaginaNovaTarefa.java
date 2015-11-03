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

	public boolean erroCadastro() {
		return driver.getPageSource().contains(
				"A tarefa não pode ser salva. Tente novamente.");
	}

	public boolean erroTituloEmBranco() {
		return driver.getPageSource().contains("Error: Titulo obrigatório!");
	}

	public boolean erroDescricaoEmBranco() {
		return driver.getPageSource().contains("Error: Descrição obrigatório!");
	}

	public boolean erroTituloMenorMinimo() {
		return driver.getPageSource().contains(
				"Error titulo: Minimo de caracteres 10!");
	}

	public boolean erroDescricaoMenorMinimo() {
		return driver.getPageSource().contains(
				"Error descrição: Minimo de caracteres 10!");
	}

	public boolean erroCadastroTudoEmBranco() {
		return erroCadastro() && erroTituloEmBranco()
				&& erroDescricaoEmBranco();
	}

}

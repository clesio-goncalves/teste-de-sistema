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

	public boolean erroCadastro() {
		return driver.getPageSource().contains(
				"O projeto não pode ser salvo. Tente novamente.");
	}

	public boolean erroTituloEmBranco() {
		return driver.getPageSource().contains("Error: Titulo obrigatório!");
	}

	public boolean erroTituloMenorMinimo() {
		return driver.getPageSource().contains(
				"Error titulo: Minimo de caracteres 10");
	}

	public boolean erroDescricaoEmBranco() {
		return driver.getPageSource().contains("Error: Descrição obrigatório!");
	}

	public boolean erroDescricaoMenorMinimo() {
		return driver.getPageSource().contains(
				"Error descrição: Minimo de caracteres 20");
	}

	public boolean erroPrevisaoFormatoIncorreto() {
		return driver.getPageSource().contains(
				"Error: Previsão - data inválida");
	}

	public boolean erroTituloCadastrado() {
		return driver.getPageSource().contains("Error: Titulo ja cadastrado!");
	}

	public boolean erroCadastroTudoEmBranco() {
		return erroCadastro() && erroTituloEmBranco()
				&& erroDescricaoEmBranco();
	}

}

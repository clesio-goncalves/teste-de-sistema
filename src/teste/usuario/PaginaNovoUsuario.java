package teste.usuario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaNovoUsuario {

	private final WebDriver driver;

	public PaginaNovoUsuario(WebDriver driver) {
		this.driver = driver;
	}

	public void cadastra(String nome, String email, String senha,
			String confirmaSenha, boolean admin) {
		WebElement txtNome = driver.findElement(By.name("nome"));
		WebElement txtEmail = driver.findElement(By.name("email"));
		WebElement txtSenha = driver.findElement(By.name("senha"));
		WebElement txtConfirmaSenha = driver.findElement(By.name("confSenha"));

		txtNome.sendKeys(nome);
		txtEmail.sendKeys(email);
		txtSenha.sendKeys(senha);
		txtConfirmaSenha.sendKeys(confirmaSenha);

		if (admin) {
			WebElement ckAdmin = driver.findElement(By.name("admin"));
			ckAdmin.click();
		}

		txtNome.submit();
	}

	public boolean erroCadastro() {
		return driver.getPageSource().contains(
				"O usuario não pode ser salvo. Tente novamente.");
	}

	public boolean erroCadastroNomeEmBranco() {
		return driver.getPageSource().contains("Error: Nome obrigatório!");
	}

	public boolean erroCadastroEmailEmBranco() {
		return driver.getPageSource().contains("Error: E-mail obrigatório!");
	}

	public boolean erroCadastroSenhaEmBranco() {
		return driver.getPageSource().contains("Error: Senha obrigatória!");
	}

	public boolean erroCadastroConfirmarSenhaEmBranco() {
		return driver.getPageSource().contains("Error: Senhas não coincidem!");
	}

	public boolean erroNomeMenorMinimo() {
		return driver.getPageSource().contains(
				"Error: Minimo de caracteres 10!");
	}

	public boolean erroEmailInvalido() {
		return driver.getPageSource().contains("Error: E-mail invalido!");
	}

	public boolean erroSenhaMenorMinimo() {
		return driver.getPageSource().contains(
				"Error senha: Mínimo de 8 caracteres.");
	}

	public boolean erroSenhaMaiorMaximo() {
		return driver.getPageSource().contains(
				"Error senha: Máximo de 12 caracteres.");
	}

	public boolean erroConfirmarSenhaInvalida() {
		return driver.getPageSource().contains("Error: Senhas não coincidem!");
	}

	public boolean erroEmailCadastrado() {
		return driver.getPageSource().contains("Error: E-mail já cadastrado!");
	}

	public boolean erroCadastroTudoEmBranco() {
		return erroCadastro() && erroCadastroNomeEmBranco()
				&& erroCadastroEmailEmBranco() && erroCadastroSenhaEmBranco();
	}

}

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

}

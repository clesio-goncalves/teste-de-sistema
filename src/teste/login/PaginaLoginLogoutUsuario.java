package teste.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaLoginLogoutUsuario {

	private WebDriver driver;

	public PaginaLoginLogoutUsuario(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost/engenharia/usuario/login");
	}

	public void login(String email, String senha) {
		WebElement txtEmail = driver.findElement(By.name("email"));
		WebElement txtSenha = driver.findElement(By.name("senha"));

		txtEmail.sendKeys(email);
		txtSenha.sendKeys(senha);

		txtEmail.submit();
	}

	public void logout() {
		driver.findElement(By.linkText("Logout")).click();
	}

	public boolean erroLogin() {
		return driver.getPageSource().contains(
				"E-mail ou senha inválidos, tente novamente!");
	}

	public boolean saiuSistema() {
		return !driver.getPageSource().contains("Logout");
	}
}
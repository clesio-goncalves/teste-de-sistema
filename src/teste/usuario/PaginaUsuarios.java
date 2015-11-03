package teste.usuario;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaUsuarios {

	private WebDriver driver;

	public PaginaUsuarios(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost/engenharia/admin/usuario");
	}

	public PaginaNovoUsuario novo() {
		driver.findElement(By.linkText("Novo")).click();
		return new PaginaNovoUsuario(driver);
	}

	public boolean existeNaListagem(String nome, String email, boolean admin) {
		return driver.getPageSource().contains(nome)
				&& driver.getPageSource().contains(email)
				&& driver.getPageSource().contains(admin ? "1" : "");
	}

	public PaginaDetalhesUsuario detalhes() {
		List<WebElement> elementos = driver.findElements(By
				.linkText("Detalhes"));
		elementos.get(elementos.size() - 1).click();

		return new PaginaDetalhesUsuario(driver);
	}

	public boolean usuarioSalvo() {
		return driver.getPageSource().contains("O usuario foi salvo");
	}
}

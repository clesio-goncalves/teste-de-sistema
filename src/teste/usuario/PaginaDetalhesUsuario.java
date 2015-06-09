package teste.usuario;

import org.openqa.selenium.WebDriver;

public class PaginaDetalhesUsuario {

	private final WebDriver driver;

	public PaginaDetalhesUsuario(WebDriver driver) {
		this.driver = driver;
	}

	public boolean existeNaListagem(String nome, String email, boolean admin) {
		return driver.getPageSource().contains(nome)
				&& driver.getPageSource().contains(email)
				&& driver.getPageSource().contains(admin ? "SIM" : "NÃO");

	}
}

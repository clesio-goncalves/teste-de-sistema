package teste.acao;

import org.openqa.selenium.WebDriver;

public class PaginaDetalheAcao {

	private final WebDriver driver;

	public PaginaDetalheAcao(WebDriver driver) {
		this.driver = driver;
	}

	public boolean existeNosDetalhes(String observacao) {
		return driver.getPageSource().contains(observacao);
	}

}

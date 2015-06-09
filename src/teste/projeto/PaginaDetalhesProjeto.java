package teste.projeto;

import org.openqa.selenium.WebDriver;

public class PaginaDetalhesProjeto {

	private final WebDriver driver;

	public PaginaDetalhesProjeto(WebDriver driver) {
		this.driver = driver;
	}

	public boolean existeNosDetalhes(String titulo, String descricao,
			String previsao) {
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(descricao)
				&& driver.getPageSource().contains(previsao);
	}

}

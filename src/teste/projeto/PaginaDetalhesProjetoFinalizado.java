package teste.projeto;

import org.openqa.selenium.WebDriver;

public class PaginaDetalhesProjetoFinalizado {

	private final WebDriver driver;

	public PaginaDetalhesProjetoFinalizado(WebDriver driver) {
		this.driver = driver;
	}

	public boolean existeNaListagem(String titulo, String descricao,
			String previsao) {
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(descricao)
				&& driver.getPageSource().contains(previsao);
	}

}

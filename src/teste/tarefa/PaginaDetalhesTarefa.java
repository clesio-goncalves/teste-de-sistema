package teste.tarefa;

import org.openqa.selenium.WebDriver;

public class PaginaDetalhesTarefa {

	private final WebDriver driver;

	public PaginaDetalhesTarefa(WebDriver driver) {
		this.driver = driver;
	}

	public boolean existeNosDetalhes(String titulo, String descricao) {
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(descricao);
	}

}

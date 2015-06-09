package teste.projeto;

import org.openqa.selenium.WebDriver;

public class PaginaProjetosFinalizados {

	private final WebDriver driver;

	public PaginaProjetosFinalizados(WebDriver driver) {
		this.driver = driver;
	}

	public boolean existeNaListagem(String titulo, String previsao) {
		// Acessa a url com projetos finalizados
		driver.get("http://localhost/engenharia/admin/projeto/finalizados");
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(previsao);
	}

}

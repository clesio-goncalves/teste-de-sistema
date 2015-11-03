package teste.tarefa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import teste.acao.PaginaNovaAcao;

public class PaginaDetalhesTarefa {

	private final WebDriver driver;

	public PaginaDetalhesTarefa(WebDriver driver) {
		this.driver = driver;
	}

	public PaginaEditarTarefa editar() {
		driver.findElement(By.linkText("Editar")).click();
		return new PaginaEditarTarefa(driver);
	}

	public PaginaNovaAcao acao() {
		driver.findElement(By.linkText("Ação")).click();
		return new PaginaNovaAcao(driver);
	}

	public boolean existeNosDetalhes(String titulo, String descricao) {
		return driver.getPageSource().contains(titulo)
				&& driver.getPageSource().contains(descricao);
	}

	public void acoes() {
		String[] url = driver.getCurrentUrl().split("/");
		int tarefa = Integer.parseInt(url[url.length - 1]);

		driver.get("http://localhost/engenharia/admin/acao/index/" + tarefa);
	}

}

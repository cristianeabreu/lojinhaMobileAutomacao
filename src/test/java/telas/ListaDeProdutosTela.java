package telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import telas.FormularioDeAdicaoDeProdutosTela;

public class ListaDeProdutosTela extends BaseTela {

    public ListaDeProdutosTela(WebDriver app) {
        super(app);
    }

    public FormularioDeAdicaoDeProdutosTela acessarFormularioAdicaoNovoProduto() {
        app.findElement(By.id("com.lojinha:id/floatingActionButton")).click();

        return new FormularioDeAdicaoDeProdutosTela(app);
    }

}
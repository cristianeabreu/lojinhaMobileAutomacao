package modulos.produto;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import telas.LoginTela;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@DisplayName("Testes Mobile do Módulo de Produtos")
public class ProdutoTest {

    private WebDriver app;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        // Abrir o APP
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName","Google Pixel 3");
        capacidades.setCapability("platform","Android");
        capacidades.setCapability("udid", "192.168.88.102:5555");
        capacidades.setCapability("appPackage", "com.lojinha");
        capacidades.setCapability("appActivity", "com.lojinha.ui.MainActivity");
        capacidades.setCapability("app", "C:\\Users\\crist\\Documents\\Arquivos de Suporte do Módulo 11\\Lojinha+Android+Nativa\\lojinha-nativa.apk");

        this.app = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);
        this.app.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @DisplayName("Validação do Valor de Produto Não Permitido")
    @Test
    public void testValidacaoDoValorDeProdutoNaoPermitido() {
        //Fazer login e cadastrar produto com valor não permitido - Instanciar classes BaseTela / LoginTela / ListaDeProdutosTela / FormulárioDeAdiçãoDeProdutosTela
        String mensagemApresentada = new LoginTela(app)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("iPhone 13")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Roxo, Verde, Azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @DisplayName("Validação de Produto Cadastrado com Sucesso")
    @Test
    public void testValidacaoDeProdutoCadastradoComSucesso() {
        // Fazer login e cadastrar um produto com sucesso
        String mensagemApresentada = new LoginTela(app)
                .informarUsuario("admin")
                .informarSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("iPhone 13")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("Roxo, Verde, Azul")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }

    @AfterEach
    public void afterEach() {
        // Sair da Aplicação
        app.quit();
    }

}

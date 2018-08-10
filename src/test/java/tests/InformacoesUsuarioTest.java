package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suport.Generator;
import suport.Screenshot;
import suport.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test =  new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();
        navegador.findElement(By.linkText("Sign in")).click();
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));
        formularioSignInBox.findElement(By.name("login")).sendKeys("camila.bernardon");
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInofrmacaoAdicionalDoUsuario (@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagemEsperada")String mensagemEsperada ){
        navegador.findElement(By.xpath("//button[text() = '+ Add more data']")).click();
        WebElement popUpAddMoreData = navegador.findElement(By.xpath("//div[@id='addmoredata']"));

        WebElement campoType = popUpAddMoreData.findElement(By.xpath("//select[@name='type']"));
        new Select(campoType).selectByVisibleText(tipo);

        popUpAddMoreData.findElement(By.xpath("//input[@placeholder='Tell us what is your contact']")).sendKeys(contato);
        popUpAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);
       // toast rounded
    }

    @Test
    public void removerUmContatoDeUmUsuario (){
        navegador.findElement(By.xpath("//span[text()='123456']/following-sibling::a")).click();
        navegador.switchTo().alert().accept();
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "C:\\Users\\camila.bernardon\\Documents\\webdriver-java\\test-report\\" + Generator.dataHoraParaArquivo() + test.getMethodName() +".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown (){
        navegador.quit();
    }
}

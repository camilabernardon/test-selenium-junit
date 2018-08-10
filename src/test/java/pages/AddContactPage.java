package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage{
    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage escolherTipoContato (String tipo){
        WebElement campoType = navegador.findElement(By.xpath("//div[@id='addmoredata']")).findElement(By.xpath("//select[@name='type']"));
        new Select(campoType).selectByVisibleText(tipo);
        return this;
    }

    public AddContactPage digigitarContato (String contato){
        navegador.findElement(By.xpath("//input[@placeholder='Tell us what is your contact']")).sendKeys(contato);
        return this;
    }

    public MePage clickSave (){
        navegador.findElement(By.linkText("SAVE")).click();
        return new MePage(navegador);
    }

    public MePage adicionarContato(String tipo, String contato){
        escolherTipoContato(tipo);
        digigitarContato(contato);
        clickSave();
        return new MePage(navegador);
    }
}

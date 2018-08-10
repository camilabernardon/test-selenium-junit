package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {

    public MePage(WebDriver navegador) {
        super(navegador);
    }

    public MePage clickMoreDataAboutYou (){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
        return this;
    }

    public AddContactPage clickButtonAddMoreDataAboutYou (){
        navegador.findElement(By.xpath("//button[text() = '+ Add more data']")).click();
        return new AddContactPage (navegador);
    }

}

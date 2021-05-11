package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By elementosTarjeta = By.xpath("//*[@class=\"card mt-4 top-card\"]");

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    public MenuPage clickInElementsCard(){
        driver.findElements(elementosTarjeta).get(0).click();
        return new MenuPage(driver);
    }

    public MenuPage clickInFormsCard(){
        driver.findElements(elementosTarjeta).get(1).click();
        return new MenuPage(driver);
    }

    public MenuPage clickInAFWCard(){
        driver.findElements(elementosTarjeta).get(2).click();
        return new MenuPage(driver);
    }

    public MenuPage clickInWidgetsCard(){
        driver.findElements(elementosTarjeta).get(3).click();
        return new MenuPage(driver);
    }

    public MenuPage clickInInteractionsCard(){
        driver.findElements(elementosTarjeta).get(4).click();
        return new MenuPage(driver);
    }

    public MenuPage clickInBSACard(){
        driver.findElements(elementosTarjeta).get(5).click();
        return new MenuPage(driver);
    }

    public String getTitle(){
        return driver.getTitle();
    }



}

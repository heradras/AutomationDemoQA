package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MenuPage {
    private WebDriver driver;
    private List<WebElement> desplegables;
    private By dropDownElements = By.className("separator");
    private By mainHeader = By.className("main-header");

    private By unfoldedItems =By.xpath("//*[@class=\"element-list collapse show\"]/ul/li");

    public MenuPage(WebDriver driver){
        this.driver=driver;
    }

     public DropDownElementsPage clickInElements(){
        driver.findElements(dropDownElements).get(0).click();
        return new DropDownElementsPage(driver);
    }

    /*public UnfoldingsFormsPage clickInForms(){
        driver.findElements(dropDownElements).get(1).click();
        return new UnfoldingsFormsPage(driver);
    }



    public UnfoldingsAFWPage clickEnAFW(){
        driver.findElements(dropDownElements).get(2).click();
        return new UnfoldingsAFWPage(driver);
    }

    public UnfoldingsWidgets clickEnWidgets(){
        driver.findElements(dropDownElements).get(3).click();
        return new UnfoldingsWidgets(driver);
    }

    public UnfoldingsInteractions clickEnInteractions(){
        driver.findElements(dropDownElements).get(4).click();
        return new UnfoldingsInteractions(driver);
    }

    public UnfoldignsBSA clickEnBSA(){
        driver.findElements(dropDownElements).get(5).click();
        return new UnfoldingsBSA(driver);
    }*/



    public String getMainHeader(){
        return driver.findElement(mainHeader).getText();
    }

    public int getNumberOfItems(){
        return driver.findElements(unfoldedItems).size();
    }
}

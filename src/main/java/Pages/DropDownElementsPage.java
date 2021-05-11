package Pages;

import org.openqa.selenium.*;
import utils.domController;
import utils.scrollToItem;

public class DropDownElementsPage {

    WebDriver driver;
    private By dropDownItemsInElements = By.xpath("//*[@class=\"element-list collapse show\"]/ul/li");

    public DropDownElementsPage(WebDriver driver){
        this.driver=driver;
    }

    public TextBoxPage clickInTextBox(){
        driver.findElements(dropDownItemsInElements).get(0).click();
        return new TextBoxPage(driver);
    }

    public CheckBoxPage clickInCheckBox(){

        driver.findElements(dropDownItemsInElements).get(1).click();
        return new CheckBoxPage(driver);
    }

    public RadioButtonPage clickInRadioButton(){

        driver.findElements(dropDownItemsInElements).get(2).click();
        return new RadioButtonPage(driver);
    }
    public WebTablesPage clickInWebTables(){
        domController.scrollToItem(driver,driver.findElements(dropDownItemsInElements).get(3));

        driver.findElements(dropDownItemsInElements).get(3).click();
        return new WebTablesPage(driver);
    }
    public void clickInButtons(){
        domController.scrollToItem(driver,driver.findElements(dropDownItemsInElements).get(4));
        driver.findElements(dropDownItemsInElements).get(4).click();
    }
    public void clickInLinks(){
        domController.scrollToItem(driver,driver.findElements(dropDownItemsInElements).get(5));
        driver.findElements(dropDownItemsInElements).get(5).click();
    }
    public void clickInBrockenLinksImages(){
        domController.scrollToItem(driver,driver.findElements(dropDownItemsInElements).get(6));
        driver.findElements(dropDownItemsInElements).get(6).click();
    }
    public void clickInUploadYDownload(){
        domController.scrollToItem(driver,driver.findElements(dropDownItemsInElements).get(7));
        driver.findElements(dropDownItemsInElements).get(7).click();
    }
    public void clickInDynamicProperties(){
        domController.scrollToItem(driver,driver.findElements(dropDownItemsInElements).get(8));
        driver.findElements(dropDownItemsInElements).get(8).click();
    }





}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBoxPage {

    WebDriver driver;
    private By userNameField=By.id("userName");
    private By userEmailField=By.id("userEmail");
    private By currentAddressField=By.id("currentAddress");
    private By permanentAddressField=By.id("permanentAddress");

    private By submitButton = By.id("submit");

    private By userNameResponse=By.id("name");
    private By userEmailResponse=By.id("email");
    private By currentAddressResponse=By.xpath("//*[@class=\"border col-md-12 col-sm-12\"]/p");
    private By permanentAddressResponse=By.xpath("//*[@class=\"border col-md-12 col-sm-12\"]/p");

    private By numberOfOutputItems = By.xpath("//*[@id=\"output\"]//p");



    public TextBoxPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillUserNameField(String input){
        driver.findElement(userNameField).sendKeys(input);
    }
    public void filluserEmailField(String input){
        driver.findElement(userEmailField).sendKeys(input);
    }

    public void fillCurrentAddressField(String input){
        driver.findElement(currentAddressField).sendKeys(input);
    }

    public void fillPermanentAddresField(String input){
        driver.findElement(permanentAddressField).sendKeys(input);
    }

    public void clickEnSubmit(){
        scrollToItem(driver,driver.findElement((submitButton)));
        driver.findElement(submitButton).click();
    }

    public String getUserNameResponse(){
        return driver.findElement(userNameResponse).getText();
    }

    public String getUserEmailResponse(){
        return driver.findElement(userEmailResponse).getText();
    }

    public String getCurrentAddressResponse(){
        System.out.println("Elementos p: "+driver.findElements(currentAddressResponse).size());
        return driver.findElements(currentAddressResponse).get(2).getText();
    }

    public String getPermanentAddressResponse(){
        return driver.findElements(permanentAddressResponse).get(3).getText();
    }

    private void scrollToItem(WebDriver driver, WebElement webElement){
        String script = "arguments[0].scrollIntoView();";
        ((JavascriptExecutor)driver).executeScript(script,webElement);
    }

    public int getNumberOfResponseItems(){
        return driver.findElements(numberOfOutputItems).size();
    }

    public boolean getErrorFrameInUserEmailField(){
        if(driver.findElement(userEmailField).getAttribute("class").equals("mr-sm-2 field-error form-control")){
            return true;
        }
        return false;

    }

}

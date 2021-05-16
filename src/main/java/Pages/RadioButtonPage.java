package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButtonPage {
    WebDriver driver;
    private By yesOption=By.id("yesRadio");
    private By impressiveOption=By.id("impressiveRadio");
    private By noOption=By.id("noRadio");

    private By clickeableYesOption = By.xpath("//*[@id=\"yesRadio\"]/../label"); //Xpath necesario para correr en chrome y en firefox
    private By clickeableImpressiveOption =By.xpath("//*[@id=\"impressiveRadio\"]/../label"); //Xpath necesario para correr en chrome y en firefox
    private By clickeableNoOption = By.xpath("//*[@id=\"noRadio\"]/..");
    private By textSuccess = By.xpath("//*[@class=\"text-success\"]");


    public RadioButtonPage(WebDriver driver){
        this.driver=driver;
    }

    public By getNoOption(){
        return noOption;
    }

    public By getYesOption(){
        return yesOption;
    }

    public By getImpressiveOption(){
        return impressiveOption;
    }

    public By getClickeableYesOption(){
        return clickeableYesOption;
    }
    public By getClickeableImpressiveOption(){
        return clickeableImpressiveOption;
    }
    public By getClickeableNoOption(){
        return clickeableNoOption;
    }
    public String getTextSuccess(){
        try {
            return driver.findElement(textSuccess).getText();
        }catch (NoSuchElementException e){
            return "No such element";
        }
    }

    public boolean getOptionState(WebElement option){
        return option.isSelected();
    }

    public void clickYesOption(){
        driver.findElement(clickeableYesOption).click();
    }

    public void clickNoOption(){
        driver.findElement(clickeableNoOption).click();
    }

    public void clickImpressiveOption(){
        driver.findElement(clickeableImpressiveOption).click();
    }


}

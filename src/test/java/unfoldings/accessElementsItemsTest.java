package unfoldings;

import Pages.DropDownElementsPage;
import Pages.MenuPage;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * accessElementsItems se refiere a los elementos dropdown de la pestaña Elements.
 * Estas pruebas comprueban que dichos elementos direccionan a la pagina adecuada
 * verificando el titulo de las paginas.
 */
public class accessElementsItemsTest extends BaseTest {

    @Test
    public void testTextBox(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInTextBox();
        assertEquals("Text Box",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());

    }

    @Test
    public void testCheckBox(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInCheckBox();
        assertEquals("Check Box",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testRadioButton(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInRadioButton();
        assertEquals("Radio Button",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testWebTables(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInWebTables();
        assertEquals("Web Tables",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testButtons(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInButtons();
        assertEquals("Buttons",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testDynamicProperties(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInDynamicProperties();
        assertEquals("Dynamic Properties",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testLinks(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInLinks();
        assertEquals("Links",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testBrokenLinksImages(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInBrockenLinksImages();
        assertEquals("Broken Links - Images",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }

    @Test
    public void testUploadAndDownload(){
        MenuPage menuPage = homePage.clickInElementsCard();
        DropDownElementsPage dropDownElementsPage = new DropDownElementsPage(driver);
        dropDownElementsPage.clickInUploadYDownload();
        assertEquals("Upload and Download",driver.findElement(By.xpath("//*[@class=\"main-header\"]")).getText());
    }
}

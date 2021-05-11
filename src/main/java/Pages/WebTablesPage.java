package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.scrollToItem;

import java.util.List;

public class WebTablesPage {
    private WebDriver driver;

    private By searchBox = By.id("searchBox");
    private By searchButton = By.id("basic-addon2");
    private By addButton = By.id("addNewRecordButton");

    private By columns = By.xpath("//*[@class=\"rt-resizable-header-content\"]");

    private WebElement firstNameColumnHeader= null;
    private WebElement lastNameColumnHeader = null;
    private WebElement ageColumnHeader = null;
    private WebElement emailColumnHeader = null;
    private WebElement salaryColumnHeader = null;
    private WebElement departmentColumnHeader = null;
    private WebElement actionColumnHeader = null;

    private By rows = By.xpath("//*[@class=\"rt-tr-group\"]/div");

    private List<WebElement> rowsList = null;

    private By previousButton= By.xpath("//*[@class=\"-previous\"]/button");
    private By nextButton = By.xpath("//*[@class=\"-next\"]/button");
    private By pageNumber = By.xpath("//*[@class=\"-pageJump\"]/input");

    private By firstNameBox = By.id("firstName");
    private By lastNameBox = By.id("lastName");
    private By emailBox = By.id("userEmail");
    private By ageBox = By.id("age");
    private By salaryBox = By.id("salary");
    private By departmentBox = By.id("department");
    private By submitButton = By.id("submit");
    private By closeButton = By.xpath("//*[@type=\"button\"]/*[@aria-hidden=\"true\"]");


    public WebTablesPage(WebDriver driver){
        this.driver=driver;

        firstNameColumnHeader= driver.findElements(columns).get(0);
        lastNameColumnHeader = driver.findElements(columns).get(1);
        ageColumnHeader = driver.findElements(columns).get(2);
        emailColumnHeader = driver.findElements(columns).get(3);
        salaryColumnHeader = driver.findElements(columns).get(4);
        departmentColumnHeader = driver.findElements(columns).get(5);
        actionColumnHeader = driver.findElements(columns).get(6);

        rowsList = driver.findElements(rows);
    }



    public void inputGenerator(String firstName,String lastName,String age,String email,String salary,String department){
        new scrollToItem(driver,driver.findElement(addButton));
        driver.findElement(addButton).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(firstNameBox));
        driver.findElement(firstNameBox).sendKeys(firstName);
        driver.findElement(lastNameBox).sendKeys(lastName);
        driver.findElement(emailBox).sendKeys(email);
        driver.findElement(ageBox).sendKeys(age);
        driver.findElement(salaryBox).sendKeys(salary);
        driver.findElement(departmentBox).sendKeys(department);
        driver.findElement(submitButton).click();
    }

}

package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.columns;
import utils.domController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
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

    public void clickInAddButton(){
        driver.findElement(addButton).click();
    }
    public By getAddButton(){
        return addButton;
    }
    public List<WebElement> getRowsList(){
        return rowsList;
    }

    public String getCellText(WebElement element,columns column){

        return element.findElements(By.xpath(".//*[@class=\"rt-td\"]")).get(getColumnIndex(column)).getText();
    }

    public void refreshRowsList(){
        rowsList=driver.findElements(rows);
    }

    public int getNumberOfCompleteRows(){
        refreshRowsList();
        int numberOfCompleteRows=0;
        for(WebElement row:rowsList){
            if(!row.findElement(By.xpath(".//*[@class=\"rt-td\"]")).getText().equals(" ")){
                numberOfCompleteRows++;
            }
        }
        return numberOfCompleteRows;

    }


    public void manualInputGenerator(String firstName,String lastName,String age,String email,String salary,String department){
        domController.scrollToItem(driver,driver.findElement(addButton));
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
    public void writeInFirstName(String text){
        driver.findElement(firstNameBox).sendKeys(text);
    }

    public void writeInLastName(String text){
        driver.findElement(lastNameBox).sendKeys(text);
    }

    public void writeInAge(String text){
        driver.findElement(ageBox).sendKeys(text);
    }

    public void writeInEmail(String text){
        driver.findElement(emailBox).sendKeys(text);
    }

    public void writeInSalary(String text){
        driver.findElement(salaryBox).sendKeys(text);
    }

    public void writeInDepartment(String text){
        driver.findElement(departmentBox).sendKeys(text);
    }

    public void inputGenerator(int numberOfInputs) throws IOException {
        String[][] data = new String[numberOfInputs][6];
        String[] elements;
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\dataWebTables.txt"));

            String line;
            for(int i=0;i<numberOfInputs;i++){
                line=bufferedReader.readLine();
                elements=line.split(",");
                for(int j=0;j<6;j++) {
                    data[i][j]=elements[j];
                }
            }
        }catch (IOException e){
            System.out.println("error: "+e);
        }



        domController.scrollToItem(driver,driver.findElement(addButton));


        for(int i=0;i<numberOfInputs;i++){
            driver.findElement(addButton).click();
            WebDriverWait wait = new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.elementToBeClickable(firstNameBox));
            driver.findElement(firstNameBox).sendKeys(data[i][0]);
            driver.findElement(lastNameBox).sendKeys(data[i][1]);
            driver.findElement(emailBox).sendKeys(data[i][2]);
            driver.findElement(ageBox).sendKeys(data[i][3]);
            driver.findElement(salaryBox).sendKeys(data[i][4]);
            driver.findElement(departmentBox).sendKeys(data[i][5]);
            driver.findElement(submitButton).click();
        }


    }



    public void writeInSearchBox(String search){
        driver.findElement(searchBox).sendKeys(search);
    }

    private int getColumnIndex(columns columnName){
        switch(columnName){
            case FIRSTNAME:   return 0;

            case LASTNAME:    return 1;

            case AGE:         return 2;

            case EMAIL:       return 3;

            case SALARY:      return 4;

            case DEPARTMENT:  return 5;

            case ACTION:      return 6;

            default:            return -1;
        }
    }
}

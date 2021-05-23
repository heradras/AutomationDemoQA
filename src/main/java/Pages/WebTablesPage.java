package Pages;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.columns;
import utils.domController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WebTablesPage {
    private WebDriver driver;

    private By searchBox = By.id("searchBox");
    private By searchButton = By.id("basic-addon2");
    private By addButton = By.id("addNewRecordButton");
    private By BoxesInForm = By.xpath("//*[@class=\"mt-2 row\"]");
    private By formTittle = By.id("registration-form-modal");

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

    private ArrayList<String> incorrectInputs = new ArrayList<>();
    private ArrayList<String> expectedOutputs = new ArrayList<>();


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

    public int getNumberOfBoxesInForm(){
        int numberOfBoxesInForm;
        do {
            clickAddButtonAndWaitFormLoad();
            numberOfBoxesInForm =driver.findElements(BoxesInForm).size();
            clickCloseButton();
            if(numberOfBoxesInForm<=0) System.out.println("getNumberOfBoxesInForm Error");
        } while(numberOfBoxesInForm <=0);

        return numberOfBoxesInForm;
    }

    public String getFormTittle(){
        return driver.findElement(formTittle).getText();
    }

    public By getAddButton(){
        return addButton;
    }

    public boolean checkRedFrame(columns col){

        WebElement box=null;
        switch (col){
            case FIRSTNAME: box=driver.findElement(firstNameBox);
                            break;
            case LASTNAME:  box=driver.findElement(lastNameBox);
                            break;
            case EMAIL:     box=driver.findElement(emailBox);
                            break;
            case AGE:       box=driver.findElement(ageBox);
                            break;
            case SALARY:    box=driver.findElement(salaryBox);
                            break;
            case DEPARTMENT:box=driver.findElement(departmentBox);
                            break;
            default:        break;

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(box.getCssValue("border-color"));
        if(box.getCssValue("border-color").equals("rgb(220, 53, 69)")){
            return true;
        }
        return false;
    }

    public List<WebElement> getRowsList(){
        return rowsList;
    }

    public String getCellText(WebElement element,columns column){
        String text=element.findElements(By.xpath(".//*[@class=\"rt-td\"]")).get(getColumnIndex(column)).getText();
        return text;
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

    /**
     * Pone a addbuton en el area visible y lo clickea, Esoera a que se cargue el departmentBox,
     * el cual es el ultimo elemento en cargar de todos los campos rellenables.
     * Rellena los campos en funcion de los parametros, y clickea en el boton submit. Espera
     * por sistema una cantidad de tiempo y termina su ejecucion.
     * @param firstName firstNameBox content.
     * @param lastName  lastNameBox content.
     * @param age ageBox content.
     * @param email emailBox content.
     * @param salary salaryBox content.
     * @param department departmentBox content.
     */


    public void manualInputGenerator(String firstName,String lastName,String age,String email,String salary,String department){
        domController.scrollToItem(driver,driver.findElement(addButton));
        driver.findElement(addButton).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(firstNameBox));
        writeInFirstName(firstName);
        writeInLastName(lastName);
        writeInAge(age);
        writeInEmail(email);
        writeInSalary(salary);
        writeInDepartment(department);

        driver.findElement(submitButton).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickAddButtonAndWaitFormLoad(){
        domController.scrollToItem(driver,driver.findElement(addButton));
        driver.findElement(addButton).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(firstNameBox));
    }

    public void clickCloseButton(){
        driver.findElement(closeButton).click();
    }

    public void manualInputGenerator(String firstName,String lastName,String age,String email,String salary,String department,int index,String text){

        List<String> data = new ArrayList<String>();

        data.add(firstName);
        data.add(lastName);
        data.add(age);
        data.add(email);
        data.add(salary);
        data.add(department);

        data.set(index,text);

        writeInFirstName(data.get(0));
        writeInLastName(data.get(1));
        writeInAge(data.get(2));
        writeInEmail(data.get(3));
        writeInSalary(data.get(4));
        writeInDepartment(data.get(5));
        clickInSubmitButton();

    }

    public void eraseAllBoxes(){
        driver.findElement(firstNameBox).clear();
        driver.findElement(lastNameBox).clear();
        driver.findElement(ageBox).clear();
        driver.findElement(emailBox).clear();
        driver.findElement(salaryBox).clear();
        driver.findElement(departmentBox).clear();
    }

    public String getWritedBoxText(){
        return driver.findElement(firstNameBox).getCssValue("value");
    }

    //public String getPyloadInFile(int column,int row,)

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

    public void clickInSubmitButton(){
        driver.findElement(submitButton).click();
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


    public ArrayList<String> cloneColumnElementsInList(int iRow,int cellIndex){

        ArrayList<String> list=new ArrayList<>();
        File file = new File("D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\incorrectInputsFirstName.xlsx");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook wb=null;
        try {
            wb = WorkbookFactory.create(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
        while(row!=null)
        {

            var cell = row.getCell(cellIndex);
            var value = cell.getStringCellValue();

            System.out.println("Valor de la entrada es " + value);
            System.out.println(cell.getCellType().toString());
            list.add(value);
            iRow++;
            row = sheet.getRow(iRow);
        }
        System.out.println("Number of cells in column "+sheet.getRow(0).getCell(cellIndex)
        +": "+list.size());
        return list;
    }





    public static Object getFormatedCell(String text){
        try {
            return Integer.parseInt(text);

        } catch (NumberFormatException nfe){
            try{
                return Boolean.parseBoolean(text);
            }catch (NumberFormatException _nfe){
                return text;
            }

        }
    }


}
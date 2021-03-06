package elements;

import Pages.DropDownElementsPage;
import Pages.MenuPage;
import base.BaseTest;
import org.apache.poi.ss.usermodel.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.columns;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.columns.*;

/**
 * Pruebas:
 * X1 - Campo de busqueda:
 *    X 1.1 - El campo busqueda selecciona elementos de texto correspondiente en cualquier columna
 *    X 1.2 - Las busquedas se realizan caracter a caracter trayendo las coincidencias de todas las columnas
 *    X 1.3 - Los resultados no distinguen mayusculas de minusculas
 * X2 - El boton add abre el formulario de introduccion de personal y agrega satisfactoriamente los datos
 *    X 2.1 - Todos los campos son obligatorios, se demuestra que se marca el marco en rojo si se omite uno
 *            y que el formulario no se envia.
 *    X 2.2 - Campo First Name:
 *       X 2.2.1 - Admite caracteres especiales y hasta un maximo de 25
 *    X 2.3 - Campo Last Name:
 *       X 2.3.1 - Admite caracteres especiales y hasta un maximo de 25
 *    X 2.4 - Campo Email:
 *       X 2.4.1 - Es obligatoria, en el campo Email, la introduccion de una arroba y un punto
 *       X 2.4.2 - El campo no admite caracteres especiales excpetuando el punto "."
 *       X 2.4.3 - El campo admite multiples puntos despues de la arroba pero no como final de cadena
 *       X 2.4.4 - El campo no posee limite de caracteres
 *       X 2.4.5 - No se admite mas de una arroba
 *    X 2.5 - Campo Age:
 *       X 2.5.1 - Solo admite numeros
 *       X 2.5.2 - Admite un maximo de 2 caracteres (0-99)
 *       X 2.5.3 - Admite edad 0
 *       X 2.5.4 - Admite edad 00
 *    X 2.6 - Campo Salary:
 *       X 2.6.1 - Solo admite numeros enteros
 *       X 2.6.2 - Admite un maximo de 10 caracteres
 *    X 2.7 - Campo Department:
 *       X 2.7.1 - Admite todos los caracteres alfanumericos y espacios
 *       X 2.7.2 - Admite un maximo de 25 caracteres
 *  3 - Clickear sobre los nombres de las columnas, en el area de datos, ordena la columna de forma ascendente
 *  4 - Volver a clickear sobre los nombres de las columnas reordena los datos de forma descendente
 *  5 - El icono de lapiz (Edit) abre el registration form con los datos introducidos para ser modificados
 *  6 - El icono de basura (Delete) elimina la fila
 *  7 - El boton Previous vuelve una pagina hacia atras cuando hay mas filas introducidas de las que admite el DropDown rows
 *  8 - El boton Next pasa a la siguiente pagina cuando hay mas filas introducidas de las que admite el DropDown rows
 *  9 - Se muestra el numero de pagina actual y la cantidad total de paginas
 *  10 - Es posible moverse entre paginas introduciendo el numero de pagina en el box
 */

public class WebTablesTests extends BaseTest {

    /**
     * Prueba positiva:
     *      Se comprueba que al ingresar una palabra en el campo de
     *      busqueda se busque en todas las columnas y se devuelvan
     *      las filas que poseen coincidencias coincidencias
     * METODO DE PRUEBA:
     *      Se introduce el texto "alden" (comprobando tambien que
     *      las busquedas no distinguen mayusculas de minusculas)
     *      y se espera que solo haya un resultado, y que corresponda
     *      a la columna First Name de la primer y unica fila
     * @throws IOException
     */

    @Test
    public void testSearchBoxCompleteCoincidence() throws IOException {
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();

        String wordToSearch = "Alden".toLowerCase();
        webTablesPage.writeInSearchBox(wordToSearch);
        webTablesPage.refreshRowsList();
        for(WebElement element:webTablesPage.getRowsList()){
            if(!webTablesPage.getCellText(element, FIRSTNAME).equals(" ")){
                assertEquals("Alden",webTablesPage.getCellText(element, FIRSTNAME));
            }
        }
    }

    /**
     * Prueba positiva:
     *      Se comprueba que al ingresar una palabra incompleta
     *      en el campo de busqueda se busque en todas las columnas
     *      y se devuelvan las filas que poseen coincidencias
     * METODO DE PRUEBA:
     *      Se introduce el texto "al" (comprobando tambien que
     *      las busquedas no distinguen mayusculas de minusculas)
     *      y se espera que dados las filas iniciales solo haya dos
     *      resultados, y que correspondan a la columna First Name
     *      de la primer y segunda fila, ya que el primer elemento
     *      posee la subcadena "al" en la columna First Name y el otro
     *      en la columna Department
     * @throws IOException
     */
    @Test
    public void testSearchBoxIncompleteCoincidence() throws IOException {
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();

        String wordToSearch = "Al".toLowerCase();
        webTablesPage.writeInSearchBox(wordToSearch);
        webTablesPage.refreshRowsList();
        assertEquals(2,webTablesPage.getNumberOfCompleteRows());
        for(WebElement element:webTablesPage.getRowsList()){
            if(!webTablesPage.getCellText(element, FIRSTNAME).equals(" ")){
                assertEquals(true,
                        webTablesPage.getCellText(element, FIRSTNAME).equals("Alden") ||
                              webTablesPage.getCellText(element, FIRSTNAME).equals("Kierra"));
            }

        }
    }

    /**
     * Prueba positiva:
     *      Se comprueba que se agregue una fila rellenando los boxes del formulario
     *      desplegado al clickear el boton add.
     * METODO DE PRUEBA:
     *      Se llama al metodo manualImputGenerator que posee las directivas para
     *      clickear en el boton add mediante el metodo clickAddButton de la clase,
     *      webTablesPage. Alli se realiza una espera implicita hasta que se encuentra
     *      cargado el primer box del formulario. Por ultimo se van llenando los campos
     *      en funcion de los parametros recibidos y se presiona el boton sumbit para
     *      crear la nueva fila de datos.
     *
     *      Para verificar los resultados se compara que el numero de filas es ahora de 4
     *      (1 m??s que antes de la introduccion de una nueva fila) y se recorre la columna
     *      first name en busca de la cadena "Joaquin"
     */

    @Test
    public void testAddButton() throws InterruptedException {
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        webTablesPage.clickAddButtonAndWaitFormLoad();
        webTablesPage.manualInputGenerator("Joaquin","Grassi","28",
                "joaquin@example.com","10000","QA Automation");
        webTablesPage.clickInSubmitAndWait(2000);
        assertEquals(4,webTablesPage.getNumberOfCompleteRows());
        boolean flagg=false;
        for(int i=0;i<webTablesPage.getNumberOfCompleteRows();i++)
            if(webTablesPage.getCellText(webTablesPage.getRowsList().get(i), FIRSTNAME).equals("Joaquin"))
                flagg=true;

        assertTrue(flagg);
    }

    /**
     * Prueba negativa:
     *      Se comprueba que al no introducirse un campo en el formulario de ingreso
     *      de datos, este se marque con una casilla roja y que el formulario no se envie
     *      para su manipulacion.
     * METODO DE PRUEBA:
     *      Primero se obtiene el numero de boxes que posee el formulario. Para ello se
     *      abre una unica vez en el proceso de prueba el formulario, se toma el numero de
     *      campos y se guarda el dato numerico en una variable y se cierra el formulario.
     *      Luego, de forma iterativa, se vuelve a abrir un formulario, se espera que cargue,
     *      se rellenan todos los campos excepto uno y se da click a submit. Se
     *      comprueba que el formulario sigue abierto (no se envi??) y se constata que el
     *      recuadro correspondiente al campo incompleto se marque con un cuadro rojo. Por ultimo se
     *      cierra el formulario y el ciclo empieza de nuevo para analizar el siguiente campo.
     *
     * @throws InterruptedException La funcion checkRedFrame utiliza una espera de un segundo entre
     *                              que se presiona submit y se constata el color del marco, es por eso
     *                              que se espera la excepcion InterruptedException.
     */

    @Test
    public void redFrameInIncompleteFields() throws InterruptedException {
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        int numberOfBoxesInForm=webTablesPage.getNumberOfBoxesInForm();

        for(int i=0;i<numberOfBoxesInForm;i++) {

            webTablesPage.clickAddButtonAndWaitFormLoad();
            webTablesPage.eraseAllBoxes();
            webTablesPage.manualInputGenerator("Joaquin","Grassi","28",
                    "joaquin@example.com","10000","QA Automation",i,"");
            webTablesPage.clickInSubmitAndWait(2000);
            assertEquals("Registration Form",webTablesPage.getFormTittle());
            assertTrue(webTablesPage.checkRedFrame(columns.values()[i]));
            //webTablesPage.eraseAllBoxes();
            webTablesPage.clickCloseButton();
        }
    }


    @Test
    public void correctBehavoirOfFirstNameBox(){
        driver.manage().window().maximize();
        String pathToTestsFile="D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\testInputsFirstName.xlsx";
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        ArrayList<String> incorrectInputs = webTablesPage.cloneColumnElementsInList(1,1,pathToTestsFile);
        ArrayList<String> expectedOutputs = webTablesPage.cloneColumnElementsInList(1,2,pathToTestsFile);
        ArrayList<String> methods = webTablesPage.cloneColumnElementsInList(1,3,pathToTestsFile);
        ArrayList<String> checkScenario = webTablesPage.cloneColumnElementsInList(1,4,pathToTestsFile);
        ArrayList<String> clickSubmitNeeded = webTablesPage.cloneColumnElementsInList(1,5,pathToTestsFile);


        for(int i=0;i<incorrectInputs.size();i++) {

            if(!webTablesPage.getInFormContext()){
                webTablesPage.eraseAllRows();
                webTablesPage.clickAddButtonAndWaitFormLoad();
            }


            String incorrectInput=incorrectInputs.get(i);
            webTablesPage.manualInputGenerator("", "Grassi", "28", "joaquin.grassi@example.com",
                    "10000", "QA Testing");
            webTablesPage.writeInFirstName(incorrectInput);
            System.out.println(incorrectInput);
            assertTrue(
                    webTablesPage.determineTest(expectedOutputs.get(i),
                            methods.get(i),
                            clickSubmitNeeded.get(i),
                            checkScenario.get(i),
                            FIRSTNAME)
            );

        }
    }

    @Test
    public void correctBehavoirOflastNameBox(){
        String pathToTestsFile = "D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\testInputsLastName.xlsx";
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        ArrayList<String> incorrectInputs = webTablesPage.cloneColumnElementsInList(1,1,pathToTestsFile);
        ArrayList<String> expectedOutputs = webTablesPage.cloneColumnElementsInList(1,2,pathToTestsFile);
        ArrayList<String> methods = webTablesPage.cloneColumnElementsInList(1,3,pathToTestsFile);
        ArrayList<String> checkScenario = webTablesPage.cloneColumnElementsInList(1,4,pathToTestsFile);
        ArrayList<String> clickSubmitNeeded = webTablesPage.cloneColumnElementsInList(1,5,pathToTestsFile);


        for(int i=0;i<incorrectInputs.size();i++) {

            if(!webTablesPage.getInFormContext()){
                webTablesPage.eraseAllRows();
                webTablesPage.clickAddButtonAndWaitFormLoad();
            }


            String incorrectInput=incorrectInputs.get(i);
            webTablesPage.manualInputGenerator("Joaquin", "", "28", "joaquin.grassi@example.com",
                    "10000", "QA Testing");
            webTablesPage.writeInLastName(incorrectInput);

            assertTrue(
                    webTablesPage.determineTest(expectedOutputs.get(i),
                            methods.get(i),
                            clickSubmitNeeded.get(i),
                            checkScenario.get(i),
                            LASTNAME)
            );
        }
    }

    @Test
    public void correctBehavoirOfAgeBox(){
        driver.manage().window().maximize();
        String pathToTestsFile = "D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\testInputsAge.xlsx";
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        ArrayList<String> incorrectInputs = webTablesPage.cloneColumnElementsInList(1,1,pathToTestsFile);
        ArrayList<String> expectedOutputs = webTablesPage.cloneColumnElementsInList(1,2,pathToTestsFile);
        ArrayList<String> methods = webTablesPage.cloneColumnElementsInList(1,3,pathToTestsFile);
        ArrayList<String> checkScenario = webTablesPage.cloneColumnElementsInList(1,4,pathToTestsFile);
        ArrayList<String> clickSubmitNeeded = webTablesPage.cloneColumnElementsInList(1,5,pathToTestsFile);




        for(int i=0;i<incorrectInputs.size();i++) {

            if(!webTablesPage.getInFormContext()){
                webTablesPage.eraseAllRows();
                webTablesPage.clickAddButtonAndWaitFormLoad();
            }


            String incorrectInput=incorrectInputs.get(i);
            webTablesPage.manualInputGenerator("Joaquin", "Grassi", "", "joaquin.grassi@example.com",
                    "10000", "QA Testing");
            webTablesPage.writeInAge(incorrectInput);

            assertTrue(
                webTablesPage.determineTest(expectedOutputs.get(i),
                                            methods.get(i),
                                            clickSubmitNeeded.get(i),
                                            checkScenario.get(i),
                                            AGE)
            );

        }
    }

    @Test
    public void correctBehavoirOfSalaryBox(){
        driver.manage().window().maximize();
        String pathToTestsFile = "D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\testInputsSalary.xlsx";
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        ArrayList<String> incorrectInputs = webTablesPage.cloneColumnElementsInList(1,1,pathToTestsFile);
        ArrayList<String> expectedOutputs = webTablesPage.cloneColumnElementsInList(1,2,pathToTestsFile);
        ArrayList<String> methods = webTablesPage.cloneColumnElementsInList(1,3,pathToTestsFile);
        ArrayList<String> checkScenario = webTablesPage.cloneColumnElementsInList(1,4,pathToTestsFile);
        ArrayList<String> clickSubmitNeeded = webTablesPage.cloneColumnElementsInList(1,5,pathToTestsFile);




        for(int i=0;i<incorrectInputs.size();i++) {

            if(!webTablesPage.getInFormContext()){
                webTablesPage.eraseAllRows();
                webTablesPage.clickAddButtonAndWaitFormLoad();
            }


            String incorrectInput=incorrectInputs.get(i);
            webTablesPage.manualInputGenerator("Joaquin", "Grassi", "28", "joaquin.grassi@example.com",
                    "", "QA Testing");
            webTablesPage.writeInSalary(incorrectInput);

            assertTrue(
                    webTablesPage.determineTest(expectedOutputs.get(i),
                            methods.get(i),
                            clickSubmitNeeded.get(i),
                            checkScenario.get(i),
                            SALARY)
            );

        }
    }


    @Test
    public void correctBehavoirOfDepartmentBox(){
        driver.manage().window().maximize();
        String pathToTestsFile = "D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\testInputsDepartment.xlsx";
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        ArrayList<String> incorrectInputs = webTablesPage.cloneColumnElementsInList(1,1,pathToTestsFile);
        ArrayList<String> expectedOutputs = webTablesPage.cloneColumnElementsInList(1,2,pathToTestsFile);
        ArrayList<String> methods = webTablesPage.cloneColumnElementsInList(1,3,pathToTestsFile);
        ArrayList<String> checkScenario = webTablesPage.cloneColumnElementsInList(1,4,pathToTestsFile);
        ArrayList<String> clickSubmitNeeded = webTablesPage.cloneColumnElementsInList(1,5,pathToTestsFile);




        for(int i=0;i<incorrectInputs.size();i++) {

            if(!webTablesPage.getInFormContext()){
                webTablesPage.eraseAllRows();
                webTablesPage.clickAddButtonAndWaitFormLoad();
            }


            String incorrectInput=incorrectInputs.get(i);
            webTablesPage.manualInputGenerator("Joaquin", "Grassi", "28", "joaquin.grassi@example.com",
                    "10000", "");
            webTablesPage.writeInDepartment(incorrectInput);

            assertTrue(
                    webTablesPage.determineTest(expectedOutputs.get(i),
                            methods.get(i),
                            clickSubmitNeeded.get(i),
                            checkScenario.get(i),
                            DEPARTMENT)
            );

        }
    }

    @Test
    public void correctBehavoirOfEmailBox(){
        driver.manage().window().maximize();
        String pathToTestsFile = "D:\\Software testing\\LearningPath\\practicaQADemo\\resources\\webTables\\testInputsEmail.xlsx";
        MenuPage menuPage = homePage.clickInElementsCard();
        var webTablesPage = new DropDownElementsPage(driver).clickInWebTables();
        ArrayList<String> incorrectInputs = webTablesPage.cloneColumnElementsInList(1,1,pathToTestsFile);
        ArrayList<String> expectedOutputs = webTablesPage.cloneColumnElementsInList(1,2,pathToTestsFile);
        ArrayList<String> methods = webTablesPage.cloneColumnElementsInList(1,3,pathToTestsFile);
        ArrayList<String> checkScenario = webTablesPage.cloneColumnElementsInList(1,4,pathToTestsFile);
        ArrayList<String> clickSubmitNeeded = webTablesPage.cloneColumnElementsInList(1,5,pathToTestsFile);




        for(int i=0;i<incorrectInputs.size();i++) {

            if(!webTablesPage.getInFormContext()){
                webTablesPage.eraseAllRows();
                webTablesPage.clickAddButtonAndWaitFormLoad();
            }


            String incorrectInput=incorrectInputs.get(i);
            webTablesPage.manualInputGenerator("Joaquin", "Grassi", "28", "",
                    "10000", "QA Automation");
            webTablesPage.writeInEmail(incorrectInput);

            assertTrue(
                    webTablesPage.determineTest(expectedOutputs.get(i),
                            methods.get(i),
                            clickSubmitNeeded.get(i),
                            checkScenario.get(i),
                            EMAIL)
            );

        }
    }

}

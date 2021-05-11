package base;

import Pages.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

/**
 * Test base que define el comportamiento inicial y final de las pruebas.
 * Todas las pruebas heredan de esta clase los campos driver y la pagina
 * inicial HomePage.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeEach
    void setUp(){
        //se setea la propiedad webdriver.chrome.driver, la cual es interpretada por selenium y
        //se setea el chromedriver.exe de la carpeta resources
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://demoqa.com/");
        //driver.manage().window().maximize();
        homePage=new HomePage(driver);
    }

   @AfterEach
    void tearDown(){
        driver.quit();
    }








}

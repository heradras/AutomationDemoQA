package base;

import Pages.HomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.browser_manager.DriverManager;
import runner.browser_manager.DriverManagerFactory;
import runner.browser_manager.DriverType;

import java.time.Duration;


/**
 * Test base que define el comportamiento inicial y final de las pruebas.
 * Todas las pruebas heredan de esta clase los campos driver y la pagina
 * inicial HomePage.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    private DriverManager driverManager;
    @BeforeEach
    void setUp(){
        //se setea la propiedad webdriver.chrome.driver, la cual es interpretada por selenium y
        //se setea el chromedriver.exe de la carpeta resources
        driverManager= DriverManagerFactory.getManager(DriverType.CHROME);
        driver=driverManager.getDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        homePage=new HomePage(driver);
        driver.findElement(By.xpath("//*[@id=\"close-fixedban\"]")).click();
    }

   @AfterEach
    void tearDown(){
        driver.quit();
    }








}

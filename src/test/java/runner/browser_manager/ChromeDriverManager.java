package runner.browser_manager;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager{
    @Override
    protected void createDriver() {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver=new ChromeDriver();
    }
}

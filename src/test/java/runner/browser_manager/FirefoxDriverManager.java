package runner.browser_manager;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.gecko.driver","resources/geckodriver.exe");
        driver=new FirefoxDriver();
    }
}

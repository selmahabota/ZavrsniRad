package tests;

import core.DriverManager;
import core.Environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() {

        driverThreadLocal.set(DriverManager.getInstance().setDriver());
        new Environment(driverThreadLocal.get()).openBrowser();
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

}

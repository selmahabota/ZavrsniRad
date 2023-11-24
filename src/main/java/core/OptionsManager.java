package core;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
    public static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1920,1080");
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--ignore-certificate-errors");
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.addArguments("headless");
        firefoxOptions.addArguments("window-size=1920,1080");
        return firefoxOptions;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--ignore-certificate-errors");
        edgeOptions.addArguments("--disable-popup-blocking");
        edgeOptions.addArguments("headless");
        edgeOptions.addArguments("window-size=1920,1080");
        return edgeOptions;
    }
}

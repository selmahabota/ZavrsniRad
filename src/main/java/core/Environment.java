package core;

import org.openqa.selenium.WebDriver;
import utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Environment {
    private String homeUrl;
    private WebDriver driver;

    private static final Logger log = LogManager.getLogger(Environment.class.getName());

    public Environment(WebDriver driver) {
        this.driver = driver;
    }

    public void openBrowser() {
        homeUrl = Utils.dotEnv().get("URL");
        log.info("Opening browser: " + Utils.dotEnv().get("BROWSER") + " navigating to: " + homeUrl);
        driver.get(homeUrl);
    }

}

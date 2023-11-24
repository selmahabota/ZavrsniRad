package dataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"username1@gmail.com", "1"},
                {"username", ""},
                {"username", "password"},
                {"", "123"},
                {"", ""}
        };
    }
}

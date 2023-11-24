package tests;

import dataProviders.DataProviders;
import listeners.TestListener;
import model.LoginUser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

import java.util.List;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpLogin() {
        loginPage = new LoginPage(driverThreadLocal.get());
    }

    @Test(description = "login user happy path with get and post")
    public void getPostTest() throws IOException {
        loginPage.getAndPost();
        loginPage.goToLoginPage()
                .logInUser(loginPage.getUniqueEmail(), loginPage.getUniquePassword());
        Assert.assertTrue(loginPage.isUserLoggedIn());
    }

    @Test(description = "Negative test cases for login user using dataProvider", dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
    public void invalidLoginTest(String username, String password) {
        loginPage.goToLoginPage()
                .logInUser(username, password);
        Assert.assertTrue(loginPage.checkIsUserLoggedIn());
    }

    @Test(description = "Negative test cases for login using json")
    public void invalidLoginTestJson() {

        List<LoginUser> list = Utils.getLoginDataFromJson();
        for (int i = 0; i < list.size(); i++) {
            loginPage.goToLoginPage()
                    .logInUser(list.get(i).getEmail(), list.get(i).getPassword());
        }
        Assert.assertTrue(loginPage.checkIsUserLoggedIn());
    }

    @Test(description = "Test for logout user")
    public void logOutUserTest() throws IOException {
        loginPage.getAndPost();
        loginPage.goToLoginPage()
                .logInUser(loginPage.getUniqueEmail(), loginPage.getUniquePassword())
                .logoutUser();
        Assert.assertTrue(loginPage.isUserLogout());
    }
}

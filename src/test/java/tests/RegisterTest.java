package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.RegisterPage;

@Listeners(TestListener.class)
public class RegisterTest extends BaseTest {
    RegisterPage registerPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpRegister() {
        registerPage = new RegisterPage(driverThreadLocal.get());
    }

    @Test
    public void registerUserTest() {
        registerPage.goToRegisterPage()
                .registerUser();
        Assert.assertTrue(registerPage.isUserRegistered());
    }

}

package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;

import java.io.IOException;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {
    CartPage cartPage;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpLogin() {
        cartPage = new CartPage(driverThreadLocal.get());
        loginPage = new LoginPage(driverThreadLocal.get());
    }

    @Test(description = "add products to cart")
    public void cartTest() {
        cartPage.clickOnElements();
        Assert.assertTrue(cartPage.cartPricesMatches());
    }

    @Test(description = "products payment in cart")
    public void cartPayingTest() throws IOException {

        cartPage.clickOnElements();
        cartPage.productsPayment();
        loginPage.getAndPost();
        loginPage.goToLoginPage()
                .logInUser(loginPage.getUniqueEmail(), loginPage.getUniquePassword());
        cartPage.productPaymentContinue();
        Assert.assertTrue(cartPage.isPaymentDone());
    }

}

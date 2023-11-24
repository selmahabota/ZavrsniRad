package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CategoryPage;

@Listeners(TestListener.class)
public class CategoryTest extends BaseTest {
    CategoryPage categoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpCategory() {
        categoryPage = new CategoryPage(driverThreadLocal.get());
    }

    @Test(description = "checking number of products in category and all products")
    public void numberOfProductsTest() {
        Assert.assertTrue(categoryPage.isNumberOfProductsSmaller());
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    By allProducts = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']");
    By allBrands = By.xpath("//div[@class='brands-name']/ul/li");
    By productsInCategory = By.cssSelector(".features_items .col-sm-4");

    public int numberOfAllProducts() {
        List<WebElement> listOfAllProducts = getElements(allProducts);
        return listOfAllProducts.size();
    }

    public int numberOfCategoryProducts() {
        clickOnRandomElement(allBrands);
        return getElements(productsInCategory).size();
    }

    public boolean isNumberOfProductsSmaller() {

        return numberOfAllProducts() > numberOfCategoryProducts();
    }
}

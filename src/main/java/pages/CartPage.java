package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    By listOfProductsAddToCart = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']//div[@class='single-products']//div[@class='productinfo text-center']/a");
    By listOfOverlayProductsAddToCart = By.xpath("//div[@class='features_items']//div[@class='col-sm-4']//div[@class='single-products']//div[@class='product-overlay']//a");
    By successAddToCartButton = By.cssSelector(".btn.btn-success.close-modal.btn-block");
    By cart = By.xpath("//ul//a[@href='/view_cart']");
    By price = By.cssSelector(".cart_price p");
    By quantity = By.cssSelector(".cart_quantity button");
    By total = By.cssSelector(".cart_total_price");
    By checkOut = By.cssSelector(".btn.btn-default.check_out");
    By registerOrLogin = By.xpath("//div[@class='modal-body']//a");
    By placeOrder = By.cssSelector(".btn.btn-default.check_out");
    By nameOnCard = By.xpath("//input[@name='name_on_card']");
    By cardNumber = By.xpath("//input[@name='card_number']");
    By cvc = By.xpath("//input[@name='cvc']");
    By expiration = By.xpath("//input[@name='expiry_month']");
    By year = By.xpath("//input[@name='expiry_year']");
    By submitButton = By.id("submit");
    By firstMessage = By.cssSelector(".title.text-center b");
    By secondMessage = By.cssSelector(".col-sm-9.col-sm-offset-1 p");

    public void clickOnRandomElementCart() {
        driver.navigate().refresh();
        List<WebElement> list = getElements(listOfProductsAddToCart);
        List<WebElement> list2 = getElements(listOfOverlayProductsAddToCart);
        Random random = new Random();
        int randomElement = random.nextInt(list.size());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", list.get(randomElement));
        for (int i = 0; i < 2; i++) {
            hoverOnWebElement(list.get(randomElement));
            Utils.waitForSeconds(3);
            list2.get(randomElement).click();
            clickOnElement(successAddToCartButton);
        }
    }

    public boolean cartPricesMatches() {
        clickOnElement(cart);
        List<WebElement> pricesList = getElements(price);
        List<WebElement> quantityList = getElements(quantity);
        List<WebElement> totalList = getElements(total);
        List<Double> totalPriceList = new ArrayList<>();
        totalPriceList.add(0.0);
        totalPriceList.add(0.0);
        boolean value = true;

        for (int i = 0; i < pricesList.size(); i++) {
            totalPriceList.set(i, removeTextFromString(pricesList.get(i).getText()) * removeTextFromString(quantityList.get(i).getText()));
            value = removeTextFromString(totalList.get(i).getText()) == totalPriceList.get(i);
        }
        return value;
    }

    public double removeTextFromString(String cijena) {
        String cijena1 = cijena.replaceAll("\\D", "");
        double cijenaDouble = Double.parseDouble(cijena1);
        return cijenaDouble;
    }

    public void productsPayment() {
        clickOnElement(cart);
        clickOnElement(checkOut);
        clickOnElement(registerOrLogin);
    }

    public void productPaymentContinue() {
        clickOnElement(cart);
        Utils.waitForSeconds(3);
        clickOnElement(checkOut);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(placeOrder));
        clickOnElement(placeOrder);
        typeIn(nameOnCard, faker.name().fullName());
        typeIn(cardNumber, faker.number().digits(16));
        typeIn(cvc, faker.number().digits(3));
        typeIn(expiration, "01");
        typeIn(year, "2025");
        clickOnElement(submitButton);
    }

    public boolean isPaymentDone() {
        return matchesExpectedText(firstMessage, "Order Placed!")
                && matchesExpectedText(secondMessage, "Congratulations! Your order has been confirmed!");
    }


}

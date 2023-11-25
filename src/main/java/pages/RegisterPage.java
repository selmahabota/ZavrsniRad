package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    private By loginOrRegisterLocator = By.xpath("//a[@href='/login']");
    private By nameSignUpLocator = By.xpath("//input[@data-qa='signup-name']");
    private By emailSignUpLocator = By.xpath("//input[@data-qa='signup-email']");
    private By signUpButton = By.xpath("//button[@data-qa='signup-button']");
    private By mrRadioLocator = By.id("uniform-id_gender1");
    private By mrsRadioLocator = By.id("uniform-id_gender2");
    private By passwordLocator = By.id("password");
    private By daysLocator = By.id("days");
    private By monthsLocator = By.id("months");
    private By yearLocator = By.id("years");
    private By newsletterCheckBoxLocator = By.id("newsletter");
    private By specialOffersCheckBoxLocator = By.id("optin");
    private By firstNameLocator = By.id("first_name");
    private By lastNameLocator = By.id("last_name");
    private By companyLocator = By.id("company");
    private By addressLocator = By.id("address1");
    private By address2Locator = By.id("address2");
    private By countryLocator = By.id("country");
    private By stateLocator = By.id("state");
    private By cityLocator = By.id("city");
    private By zipCodeLocator = By.id("zipcode");
    private By mobileNumberLocator = By.id("mobile_number");
    private By createAccountButton = By.xpath("//form[@action='/signup']//button");
    private By messageAccountCreatedLocator = By.xpath("//h2[@class='title text-center']/b");
    private By message2AccountCreatedLocator = By.xpath("//div[@class='col-sm-9 col-sm-offset-1']//p[1]");
    private By logOutLocator = By.xpath("//a[@href='/logout']");

    private String username;
    private String email;
    private String password;

    public RegisterPage goToRegisterPage() {
        clickOnElement(loginOrRegisterLocator);
        return this;
    }

    public RegisterPage registerUser() {
        username = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        typeIn(nameSignUpLocator, username);
        typeIn(emailSignUpLocator, email);
        clickOnElement(signUpButton);
        clickOnElement(mrRadioLocator);
        typeIn(passwordLocator, password);
        selectDay();
        selectMonth();
        selectYear();
        clickOnElement(newsletterCheckBoxLocator);
        clickOnElement(specialOffersCheckBoxLocator);
        typeIn(firstNameLocator, faker.name().firstName());
        typeIn(lastNameLocator, faker.name().lastName());
        typeIn(companyLocator, faker.name().name());
        typeIn(addressLocator, faker.address().fullAddress());
        typeIn(address2Locator, faker.address().fullAddress());
        selectCountry();
        typeIn(stateLocator, faker.address().state());
        typeIn(cityLocator, faker.address().city());
        typeIn(zipCodeLocator, faker.address().zipCode());
        typeIn(mobileNumberLocator, faker.number().digits(8));
        clickOnElement(createAccountButton);
        return this;
    }

    public void selectDay() {
        Select dropDownMenuDays = new Select(getElement(daysLocator));
        dropDownMenuDays.selectByVisibleText("1");
    }

    public void selectMonth() {
        Select dropDownMenuMonths = new Select(getElement(monthsLocator));
        dropDownMenuMonths.selectByVisibleText("January");
    }

    public void selectYear() {
        Select dropDownMenuMonths = new Select(getElement(yearLocator));
        dropDownMenuMonths.selectByVisibleText("1985");
    }

    public void selectCountry() {
        Select dropDownMenuCountry = new Select(getElement(countryLocator));
        dropDownMenuCountry.selectByVisibleText("India");
    }

    public boolean isUserRegistered() {
        return matchesExpectedText(messageAccountCreatedLocator, "Account Created!")
                && matchesExpectedText(message2AccountCreatedLocator, "Congratulations! Your new account has been successfully created!");

    }

}

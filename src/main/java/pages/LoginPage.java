package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.time.Duration;
import java.util.List;

@Getter
public class LoginPage extends BasePage {
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By loginOrRegisterLocator = By.xpath("//a[@href='/login']");
    private By emailLocator = By.xpath("//input[@data-qa='login-email']");
    private By passwordLocator = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By deleteAccountLocator = By.xpath("//a[@href='/delete_account']");
    private By logOutTextLocator = By.xpath("//a[@href='/logout']");
    private By loginForm = By.xpath("//div[@class='col-sm-4 col-sm-offset-1']");
    private By signupForm = By.xpath("//div[@class='signup-form']");
    private String uniqueEmail;
    private String uniquePassword;

    public LoginPage goToLoginPage() {
        clickOnElement(loginOrRegisterLocator);
        return this;
    }

    public LoginPage logInUser(String email, String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailLocator));
        typeIn(emailLocator, email);
        typeIn(passwordLocator, password);
        clickOnElement(loginButton);
        return this;
    }

    public LoginPage logoutUser() {
        clickOnElement(logOutTextLocator);
        return this;
    }

    public boolean isUserLoggedIn() {
        return matchesExpectedText(deleteAccountLocator, "Delete Account")
                && matchesExpectedText(logOutTextLocator, "Logout");
    }

    public boolean checkIsUserLoggedIn() {
        return matchesExpectedTextString("https://automationexercise.com/login", driver.getCurrentUrl());
    }

    public boolean isUserLogout() {
        return matchesExpectedText(loginOrRegisterLocator, "Signup / Login")
                && getElement(loginForm).isDisplayed()
                && getElement(signupForm).isDisplayed();
    }

    public void getAndPost() throws IOException {

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        String urlGet = "https://automationexercise.com/signup";
        URL objGet = new URL(urlGet);
        HttpURLConnection conGet = (HttpURLConnection) objGet.openConnection();

        conGet.setRequestMethod("GET");

        conGet.getResponseCode();

        String csrfToken = "";
        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        for (HttpCookie cookie : cookies) {
            if (cookie.getName().equals("csrftoken")) {
                csrfToken = "csrftoken=" + cookie.getValue();
            }
        }

        String csrfMiddlewareToken = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conGet.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            int position = response.indexOf("name=\"csrfmiddlewaretoken\" value=") + 34;
            csrfMiddlewareToken = "csrfmiddlewaretoken=" + response.substring(position, position + 64);
        }

        String urlPost = "https://automationexercise.com/signup";
        URL objPost = new URL(urlPost);
        HttpURLConnection conPost = (HttpURLConnection) objPost.openConnection();

        conPost.setRequestMethod("POST");
        conPost.setRequestProperty("cookie", csrfToken);
        conPost.setRequestProperty("referer", "https://automationexercise.com/signup");

        conPost.setDoOutput(true);

        String uniqueName = faker.name().firstName();
        uniqueEmail = faker.internet().emailAddress();
        uniquePassword = faker.internet().password();

        String postData = csrfMiddlewareToken +
                "&title=Mr" +
                "&name=" + uniqueName +
                "&email_address=" + uniqueEmail +
                "&password=" + uniquePassword +
                "&days=1" +
                "&months=1" +
                "&years=1954" +
                "&first_name=" + uniqueName +
                "&last_name=" + faker.name().lastName() +
                "&company=" + faker.name().name() +
                "&address1=" + faker.address().fullAddress() +
                "&address2=" + faker.address().fullAddress() +
                "&country=India" +
                "&state=" + faker.address().state() +
                "&city=" + faker.address().city() +
                "&zipcode=" + faker.address().zipCode() +
                "&mobile_number=" + faker.number().digits(8) +
                "&form_type=create_account";

        try (DataOutputStream wr = new DataOutputStream(conPost.getOutputStream())) {
            wr.writeBytes(postData);
            wr.flush();
        }

        //int responseCodePost = conPost.getResponseCode();
        //System.out.println("HTTP Response Code: " + responseCodePost);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conPost.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //  System.out.println("Response: " + response);
        }
    }
}

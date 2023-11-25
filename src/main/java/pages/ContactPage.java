package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContactPage extends BasePage {
    public ContactPage(WebDriver driver) {
        super(driver);
    }

    By contactUs = By.xpath("//a[@href='/contact_us']");
    By name = By.xpath("//input[@name='name']");
    By email = By.xpath("//input[@name='email']");
    By subject = By.xpath("//input[@name='subject']");
    By message = By.id("message");
    By chooseFileButton = By.xpath("//input[@name='upload_file']");
    By submitButton = By.xpath("//input[@name='submit']");
    By firstMessage = By.cssSelector(".status.alert.alert-success");
    By title = By.cssSelector(".contact-form h2");

    public void CreateWriteAndAttachTextFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/txtfile/output.txt"));
            writer.write("hello this is first line");
            writer.write("\nthis is second line");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clickOnElement(contactUs);
        typeIn(name, faker.name().fullName());
        typeIn(email, faker.internet().emailAddress());
        typeIn(subject, "Subject");
        typeIn(message, faker.name().fullName() + " " + faker.address());
        String absoluteFilePath = System.getProperty("user.dir") + "/src/main/java/txtfile/output.txt";
        getElement(chooseFileButton).sendKeys(absoluteFilePath);
        clickOnElement(submitButton);
        driver.switchTo().alert().accept();
    }
    public boolean isContactFormSuccessfullySubmit() {
        return matchesExpectedText(firstMessage, "Success! Your details have been submitted successfully.")
                && matchesExpectedText(title, "Get In Touch");
    }
}

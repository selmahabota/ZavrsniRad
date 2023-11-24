package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactPage;

@Listeners(TestListener.class)
public class ContactTest extends BaseTest {
    ContactPage contactPage;

    @BeforeMethod(alwaysRun = true)
    public void setUpLogin() {
        contactPage = new ContactPage(driverThreadLocal.get());
    }

    @Test(description = "filling out the contact form")
    public void contactTest() {
        contactPage.CreateWriteAndAttachTextFile();
        Assert.assertTrue(contactPage.isContactFormSuccessfullySubmit());
    }
}

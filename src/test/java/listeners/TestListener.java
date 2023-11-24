package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {
    private static final Logger log = LogManager.getLogger(TestListener.class.getName());
    ExtentReports extentReports = new ExtentReports();
    File file = new File(System.getProperty("user.dir") + "/target/eReport.html");

    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
    ExtentTest extentTest;

    private void createReport(ITestResult result) {
        sparkReporter.config(ExtentSparkReporterConfig.builder()
                .theme(Theme.DARK)
                .documentTitle("Selenium test")
                .build());
        extentReports.attachReporter(sparkReporter);
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("STARTING TEST: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        createReport(result);
        extentTest.log(Status.PASS, "Test: " + result.getMethod().getMethodName() + " PASSED" + result.getMethod().getDescription());
        log.info("TEST METHOD " + result.getMethod().getMethodName() + " PASSED");
        extentReports.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        takeScreenshot(driver);
        createReport(result);
        extentTest.log(Status.FAIL, "Test: " + result.getMethod().getMethodName() + " FAILED");
        log.error("TEST METHOD " + result.getMethod().getMethodName() + " FAILED");
        extentReports.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    private void takeScreenshot(WebDriver driver) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
        String fileName = formatter.format(date);

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

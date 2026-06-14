package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import utils.ExtentManager;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports extent;

    @BeforeSuite
    public void setupReport() {

        extent = ExtentManager.getReportInstance();
    }

    @BeforeMethod
    public void setup() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        driver.get(
            "https://testautomationpractice.blogspot.com/");
    }

    @AfterMethod
    public void tearDown() {

        if(driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {

        if(extent != null) {
            extent.flush();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
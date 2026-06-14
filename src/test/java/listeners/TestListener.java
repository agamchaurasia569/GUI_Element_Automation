package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    ExtentReports extent =
            ExtentManager.getReportInstance();

    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(
                result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        try {

            BaseTest base =
                    (BaseTest) result.getInstance();

            String path =
                    ScreenshotUtil.takeScreenshot(
                            base.getDriver(),
                            result.getName());

            test.addScreenCaptureFromPath(path);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}
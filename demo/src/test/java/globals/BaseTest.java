package globals;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static WebDriver driver;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        try {
            Desktop.getDesktop().browse(new File("test-output/extent-report.html").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String captureScreenshot(String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = "test-output/screenshots/" + screenshotName + "_" + timestamp + ".png";

        // Ensure the folder exists
        File screenshotDir = new File("test-output/screenshots/");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}

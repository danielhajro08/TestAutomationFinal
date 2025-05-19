package globals;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


import org.testng.annotations.*;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;

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
}

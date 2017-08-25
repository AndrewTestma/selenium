package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
public class TestBaseCase {
    public static WebDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static String reportLocation="ExtentReport.html";
    public  Logger log=LogManager.getLogger(this.getClass().getName());
    @BeforeSuite
    public void initializationExtentReport(){
        /*initialization ExtentReport*/
        extentReports=new ExtentReports(reportLocation,true);
        extentReports
                .addSystemInfo("Author","Andrew");
    }
    @BeforeClass
    public void startSetup(){
        /*initialization WebDriver*/
        log.entry();
        log.info("测试开始");
    }
    @AfterClass
    public void endStart(){
        log.exit();
    }
}

package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
    @AfterMethod
    public void ExtentResult(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL,result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            extentTest.log(LogStatus.SKIP,result.getThrowable());
        }else{
            extentTest.log(LogStatus.PASS,"Pass");
        }
        extentReports.endTest(extentTest);
        extentReports.flush();
    }
    @AfterSuite
    public void closeExtentReport(){
        extentReports.flush();
        extentReports.close();
    }
    public static ExtentReports getextent() {
        return extentReports;
    }

}

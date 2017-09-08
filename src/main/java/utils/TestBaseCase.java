package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public static StringBuffer sb=new StringBuffer();
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
        log.info("【开始执行测试】");
        System.setProperty("webdriver.chrome.driver","./src/main/resources/driver/Chromedriver.exe");
        driver=new ChromeDriver();
    }
    @AfterClass
    public void endStart(){
        log.exit();
        driver.close();
        driver.quit();
    }
    @AfterMethod
    public void ExtentResult(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            if(result.getParameters().length>0) {
                extentTest.log(LogStatus.PASS, "参数:" + result.getParameters()[0]);
            }
            //extentTest.log(LogStatus.FAIL,result.getThrowable());
            extentTest.log(LogStatus.FAIL,extentTest.addBase64ScreenShot(Assertion.screenShotPath),result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            if(result.getParameters().length>0) {
                extentTest.log(LogStatus.PASS, "参数:" + result.getParameters()[0]);
            }
            extentTest.log(LogStatus.SKIP,result.getThrowable());
        }else{
            if(result.getParameters().length>0) {
                extentTest.log(LogStatus.PASS, "参数:" + result.getParameters()[0]);
            }
            extentTest.log(LogStatus.PASS,"成功",extentTest.addBase64ScreenShot(Assertion.screenShotPath));
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

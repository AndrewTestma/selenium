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
    public static int types;
    public DriverManager manager = new DriverManager();
    public  Logger log=LogManager.getLogger(this.getClass().getName());

    @BeforeSuite
    @Parameters({"browserType","driverPath"})
    public void initializationExtentReport(String browserType,String driverPath){
        /*initialization ExtentReport*/
        extentReports=new ExtentReports(reportLocation,true);
        extentReports
                .addSystemInfo("Author","Andrew");
        manager.setupDriver(browserType,driverPath);
        driver=manager.getDriver();
    }
    @BeforeTest
    @Parameters({"type"})
    public void startSetup(int type){
        /*initialization WebDriver*/
        log.entry();
        log.info("【开始执行测试】");
        this.types=type;
        //System.setProperty("webdriver.chrome.driver","./src/main/resources/driver/Chromedriver.exe");
        //driver=new ChromeDriver();
        this.driver.manage().window().maximize();
    }
    @AfterTest
    public void endStart(){
        log.exit();
        //driver.close();
        //driver.quit();
        manager.quitDriver();
    }
    @AfterMethod
    public void ExtentResult(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL,extentTest.addBase64ScreenShot(Assertion.screenShotPath),result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            extentTest.log(LogStatus.SKIP,result.getThrowable());
        }else{
            extentTest.log(LogStatus.PASS,"成功");
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

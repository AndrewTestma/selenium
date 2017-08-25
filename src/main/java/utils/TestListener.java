package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/8/25 0025
 */
public class TestListener extends TestListenerAdapter {
    public static Logger logger= LogManager.getLogger(TestListener.class);
    ExtentReports extent;
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        logger.info("测试用例:"+tr.getName()+"---start");
        extent=TestBaseCase.getextent();
        //extentTest=extent.startTest(tr.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        logger.info("【" +tr.getMethod().getDescription() + " Failure】");
        //extentTest.log(LogStatus.FAIL, tr.getThrowable());
        extent.endTest(extentTest);

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        logger.info("【" + tr.getMethod().getDescription() + " Skipped】");
        //extentTest.log(LogStatus.SKIP, "SKIP");
        extent.endTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        logger.info("【" + tr.getMethod().getDescription() + " Success】");
        //extentTest.log(LogStatus.PASS, "Pass");
        //extent=TestBaseCase.getextent();
        extent.endTest(extentTest);
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }
}

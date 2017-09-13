package utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import utils.Locator.ByType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/25 0025
 */
public class Assertion extends TestBaseCase{
    public static ExtentTest verification=extentReports.startTest("验证点");
    public static ExtentTest  parameter=extentReports.startTest("参数");
    public static ExtentTest screenshot;
    //public static  ExtentTest errorLog=extentReports.startTest("错误信息");
    public static String screenShotPath;
    public static int i=0;
    public static String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date).toString();
    }
    public static void snapshotInfo(){
        ScreenShot screenShot=new ScreenShot(driver);
        Date nowDate=new Date();
        screenShot.setscreenName(Assertion.formatDate(nowDate));
        screenShotPath=screenShot.takeScreenshot();
    }
    private static Logger log= LogManager.getLogger(Assertion.class.getName());
    /**
     * 验证页面是否存在某字符
     * @param--exceptStr 预期值
     * @param--message 验证中文描述
     * */
    public static void  verityTextPresentPrecision(String exceptStr,String message,String parameterStr){
        String verityStr="【Assert验证】："+"页面是否存在【"+exceptStr+"】字符串";
        log.info(verityStr);
        Boolean flag;
        try{
            exceptStr="//*[text()=\""+exceptStr+"\"]";
            driver.findElements(By.xpath(exceptStr));
            flag=true;
        }catch(Exception e){
            flag=false;
        }
        try{
            Assert.assertTrue(flag);
            AssertPassLog(verityStr,parameterStr);
        }catch(Error f){
            AssertFailedLog(verityStr,parameterStr);
        }
    }
    /**
     * 验证页面是否存在某字符
     * @param exceptStr  预期值
     * @param frame 查找文本所在frame
     * @param Message 验证中文描述
     * @param parameterStr 测试数据
     * */
    public static  void verityTextPresentPrecision(String exceptStr,String frame,String Message,String parameterStr)
    {
        ElementAction action=new ElementAction();
        String except="//*[text()='"+exceptStr+"']";
        Locator locator=new Locator(except,ByType.xpath,"",frame,"da");
        String verityStr="【Assert验证】:"+"页面是否出现"+"【"+"预期值："+exceptStr+"】"+"字符串";
        Boolean flag;
        log.info(Message+":"+verityStr);
        try {
            WebElement webElement=action.findElement(locator);
            flag=true;
        } catch (Exception e) {
            flag=false;
        }
        try {
            Assert.assertTrue(flag);
            AssertPassLog(verityStr,parameterStr);
        } catch (Error f) {
            Assertion.snapshotInfo();
            AssertFailedLog(verityStr,parameterStr);
        }
    }
    /**
     * 验证成功
     * @param verityStr 验证信息
     * @param parameterStr 测试数据
     * */
    public static void AssertPassLog(String verityStr,String parameterStr){
        log.info("【Assert验证  pass!】");
         writeExtentReport(verityStr,parameterStr,"PASS");
    }
    /**
     * 验证失败
     * @param verityStr 验证信息
     * @param parameterStr 测试数据
     * */
    public static void AssertFailedLog(String verityStr,String parameterStr){
        log.error("【Assert验证  failed!】");
        writeExtentReport(verityStr,parameterStr,"FAILED");
    }
    /**
     * @将验证结果及测试数据写入测试报告中
     * @param status 验证状态
     * */
    public static void writeExtentReport(String verityStr,String parameterStr,String status){
        parameter.log(LogStatus.INFO,parameterStr,"正常建站数据");
        if(status.equals("PASS")){
            verification.log(LogStatus.PASS,verityStr,"PASS");
        }else{
            verification.log(LogStatus.FAIL,verityStr,"FAILED");
            screenshot=extentReports.startTest("截图");
            screenshot.log(LogStatus.FAIL,screenshot.addBase64ScreenShot(screenShotPath));
            extentTest.appendChild(screenshot);
        }
        if(i==0){
            extentTest.appendChild(parameter);
            extentTest.appendChild(verification);
            extentReports.flush();
            i++;
        }
    }
}

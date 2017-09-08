package utils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import utils.Locator.ByType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
public class Assertion extends TestBaseCase{
    public static String screenShotPath;
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
    public static void  verityTextPresentPrecision(String exceptStr,String message){
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
            AssertPassLog();
        }catch(Error f){
            AssertFailedLog();
        }
    }
    /**
     * 验证页面是否存在某字符
     * @param exceptStr  预期值
     * @param Message 验证中文描述
     * @param frame 查找文本所在frame
     * */
    public static  void verityTextPresentPrecision(String exceptStr,String frame,String Message)
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
            AssertPassLog();
        } catch (Error f) {
            AssertFailedLog();
            Assertion.snapshotInfo();
        }
    }

    public static void AssertPassLog(){
        log.info("【Assert验证  pass!】");
    }
    public static void AssertFailedLog(){
        log.error("【Assert验证  failed!】");
    }
}

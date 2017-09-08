package utils;

import com.google.common.io.Files;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;


/**
 * @Author:Andrew
 * @Description:截图工具类
 * @Date 2017/9/8 0008
 */
public class ScreenShot {
    public WebDriver driver;
    private String screenName;
    Logger log= LogManager.getLogger(this.getClass().getName());
    public ScreenShot(WebDriver driver){
        this.driver=driver;
    }
    public void setscreenName(String screenName){
        this.screenName=screenName;
    }
    private void takeScreenshot(String screenPath){
        File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            Files.copy(scrFile,new File(screenPath));
            log.error("错误截图:"+screenPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  String takeScreenshot(){
        String screenName =this.screenName+ ".jpg";
        File dir = new File("snapshot");
        if (!dir.exists()){
            dir.mkdirs();
        }
        //dir.getAbsolutePath() +
        String screenPath = "snapshot\\" + screenName;
        this.takeScreenshot(screenPath);
        return screenPath;
    }
}

package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author:Andrew
 * @Description:webdriver管理类
 * @Date 2017/10/10 0010
 */
public class DriverManager {
    public static ThreadLocal<WebDriver> ThreadDriver=new ThreadLocal<WebDriver>();
    private String  browserType;//浏览器类型
    private String driverPath;//远程地址

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public  void setupDriver(String browser,String path){
        setBrowserType(browser);
        setDriverPath(path);
    }
    /**
     * @see退出浏览器
     * */
    public  void quitDriver(){
        getDriver().quit();
        DriverManager.ThreadDriver.set(null);
    }
    /**
     * @see获取driver
     * */
    public WebDriver getDriver() {
        WebDriver driver= DriverManager.ThreadDriver.get();
        if(driver==null) {
            if (this.browserType.equals("chrome")) {
                DesiredCapabilities dc = DesiredCapabilities.chrome();
                try {
                    driver =new EventFiringWebDriver(new RemoteWebDriver(new URL(this.driverPath), dc));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                ThreadDriver.set(driver);
            } else if (this.browserType.equals("ie")) {
                DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
                try {
                    driver =new EventFiringWebDriver(new RemoteWebDriver(new URL(this.driverPath), dc));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                ThreadDriver.set(driver);
            }
        }
        return driver;
    }
}

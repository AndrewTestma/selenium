package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.Locator.ByType;
/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/8/28 0028
 */
public class ElementAction extends TestBaseCase {
    public static ArrayList<Exception> noSuchElementExceptions=new ArrayList<Exception>();
    public Logger log= LogManager.getLogger(this.getClass().getName());
    private String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date).toString();
    }
    /**
     * @自动切换Frame
     * @Date:2017.8.28
     * */
    public List<String>  switchFrame(){
        List<String> frameName=new ArrayList<String>();
        Locator iframe=new Locator("iframe", ByType.tagName,"","");
        Locator frame=new Locator("frame", ByType.tagName,"","");
        List<WebElement> element=findElements(iframe);
        if(element.size()>0){
            for(WebElement ent:element){
                if(ent.getAttribute("id")!=""){
                    System.out.println(ent.getAttribute("id"));
                } else{
                    System.out.println(ent.getAttribute("name"));
                }
            }
        }
        return frameName;
    }
    public void switchFrame(String frameName){
        driver.switchTo().frame(frameName);
        //switchFrame();
    }
    /**
     * @see查找单个元素
     * */
    public WebElement findElement(final Locator locator){
        WebElement webElement = null;
        try{
            webElement=(new WebDriverWait(driver,10).until(
                    new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver input) {
                            WebElement element;
                            element=getElement(locator);
                            return element;
                        }
                    }
            ));
        }catch(NoSuchElementException e){
            log.error("无法定位页面元素");
        }
        return webElement;
    }
    /**
     * @see查找一组元素
     * */
    public List<WebElement> findElements(final Locator locator){
        List<WebElement> webElement = null;
        try{
            webElement=(new WebDriverWait(driver,10).until(
                    new ExpectedCondition<List<WebElement>>() {
                        @Override
                        public List<WebElement> apply(WebDriver input) {
                            List<WebElement> element;
                            element=getElements(locator);
                            return element;
                        }
                    }
            ));
        }catch(NoSuchElementException e){
            log.error("无法定位页面元素");
        }
        return webElement;
    }
    /**
     * @see通过定位信息获取单个元素
     * */
    public WebElement getElement(Locator locator){
    log.info("查找元素："+locator.getLocalorName()+"查找方式："+"[By."+locator.getBy()+":"+locator.getElement()+"]");
    WebElement webElement;
        switch (locator.getBy())
        {
            case xpath :
                webElement=driver.findElement(By.xpath(locator.getElement()));
                break;
            case id:
                webElement=driver.findElement(By.id(locator.getElement()));
                break;
            case cssSelector:
                webElement=driver.findElement(By.cssSelector(locator.getElement()));
                break;
            case name:
                webElement=driver.findElement(By.name(locator.getElement()));
                break;
            case className:
                webElement=driver.findElement(By.className(locator.getElement()));
                break;
            case linkText:
                webElement=driver.findElement(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                webElement=driver.findElement(By.partialLinkText(locator.getElement()));
                break;
            case tagName:
                webElement=driver.findElement(By.tagName(locator.getElement()));
                break;
            default :
                webElement=driver.findElement(By.xpath(locator.getElement()));
                break;

        }
        return webElement;
    }
    /**
     * @see通过定位信息获取一组元素
     * */
    public List<WebElement> getElements(Locator locator){
        log.info("查找元素："+locator.getLocalorName()+"查找方式："+"[By."+locator.getBy()+":"+locator.getElement()+"]");
        List<WebElement> webElement;
        switch (locator.getBy())
        {
            case xpath :
                webElement=driver.findElements(By.xpath(locator.getElement()));
                break;
            case id:
                webElement=driver.findElements(By.id(locator.getElement()));
                break;
            case cssSelector:
                webElement=driver.findElements(By.cssSelector(locator.getElement()));
                break;
            case name:
                webElement=driver.findElements(By.name(locator.getElement()));
                break;
            case className:
                webElement=driver.findElements(By.className(locator.getElement()));
                break;
            case linkText:
                webElement=driver.findElements(By.linkText(locator.getElement()));
                break;
            case partialLinkText:
                webElement=driver.findElements(By.partialLinkText(locator.getElement()));
                break;
            case tagName:
                webElement=driver.findElements(By.tagName(locator.getElement()));
                break;
            default :
                webElement=driver.findElements(By.xpath(locator.getElement()));
                break;

        }
        return webElement;
    }
}

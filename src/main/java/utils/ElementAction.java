package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/8/28 0028
 */
public class ElementAction extends TestBaseCase {
    //public static ArrayList<Exception> noSuchElementExceptions=new ArrayList<Exception>();
    //public Logger log= LogManager.getLogger(this.getClass().getName());
   /* private String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date).toString();
    }*/
    /**
     * @see查找单个元素
     * */
    public WebElement findElement(final Locator locator){
        WebElement webElement = null;
        try{
                webElement=(new WebDriverWait(driver,3).until(
                        new ExpectedCondition<WebElement>() {
                            public WebElement apply(WebDriver input) {
                                WebElement element=null;
                                int k=0;
                                while(element==null) {
                                    if (k == 1) {
                                        if (element == null && locator.getframe().split("/").length >= 1 && locator.getframe().split("/")[0] != "") {
                                            String[] array = locator.getframe().split("/");
                                            int i = 0;
                                            driver.switchTo().defaultContent();
                                            while (array.length > i) {
                                                driver.switchTo().frame(array[i]);
                                                i++;
                                            }
                                            log.info("【当前Frame】:"+array[i-1]);
                                        }
                                        element=getElement(locator);
                                    }
                                    k++;
                                }
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
                        public List<WebElement> apply(WebDriver input) {
                            List<WebElement> element=null;
                            int k=0;
                            while(element==null) {
                                if (k == 1) {
                                    if (element == null && locator.getframe().split("/").length >= 1 && locator.getframe().split("/")[0] != "") {
                                        String[] array = locator.getframe().split("/");
                                        int i = 0;
                                        driver.switchTo().defaultContent();
                                        while (array.length > i) {
                                            driver.switchTo().frame(array[i]);
                                            i++;
                                        }
                                        log.info("【当前Frame】:"+array[i-1]);
                                    }
                                    element=getElements(locator);
                                }
                                k++;
                            }
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
    /**
     * 点击元素
     * */
    public void click(Locator locator){
        WebElement webElement=null;
        try{
            webElement=findElement(locator);
            webElement.click();
            log.info("点击："+locator.getLocalorName()+"-->点击成功");
        }catch (NoSuchElementException e){
            log.error("找不到元素："+locator.getLocalorName()+"-->点击失败");
        }
    }
    /**
     * 点击一组元素中的某一个
     * @param locator 元素对象
     * @param attributesName 元素属性名称
     * @param attributesValue 元素属性值
     * */
    public void clicks(Locator locator,String attributesName,String  attributesValue ){
        List<WebElement>webElements=null;
        try {
            webElements=findElements(locator);
            for(WebElement element:webElements){
                if(element.getAttribute(attributesName).equals(attributesValue)){
                    element.click();
                    log.info("点击："+locator.getLocalorName()+"-->点击成功");
                    break;
                }
            }
        }catch(Exception e){
            log.error("找不到元素："+locator.getLocalorName()+"-->点击失败");
        }
    }
    /**
     * 输入测试数据
     * @param locator 元素对象
     * @param value 测试数据
     * */
    public void sendKey(Locator locator,String value){
        WebElement webElement=null;
        try{
            webElement=findElement(locator);
            webElement.sendKeys(value);
            log.info(locator.getLocalorName()+"输入: "+value);
        }catch(Exception e){
            log.error("找不到元素："+locator.getLocalorName());
        }
    }
    /**
     * 处理蛋疼的复制粘贴元素输入
     * @param locator 测试对象
     * @param i 元素下标
     * @param value 测试数据
     * */
    public void sendKey(Locator locator,int i,String value){
        List<WebElement> webElements=null;
        try{
            webElements=findElements(locator);
            webElements.get(i).sendKeys(value);
            log.info(locator.getLocalorName()+"输入: "+value);
        }catch(Exception e){
            log.error("找不到元素："+locator.getLocalorName());
        }
    }
}

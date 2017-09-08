package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * @Author:Andrew
 * @Description:定位信息图
 * @Date 2017/9/8 0008
 */
public class BaseAction {
    protected HashMap<String,Locator>locatorHashMap;
    public String path=null;
    Logger log= LogManager.getLogger(this.getClass().getName());
    public void setXmlObjectPath(String path){
        this.path=path;
    }
    public BaseAction(){}
    public void getLocatorHashMap(){
        XmlReadUtil xmlReadUtil=new XmlReadUtil();
        try{
            locatorHashMap=xmlReadUtil.readXMLDocument(path,this.getClass().getCanonicalName());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 从对象库获取定位信息
     * */
    public Locator getLocator(String locatorName){
        Locator locator;
        locator=locatorHashMap.get(locatorName);
        if(locator==null){
            log.error("XML存储中查找不到："+locatorName+"元素信息");
        }
        return locator;
    }
}

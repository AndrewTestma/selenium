package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import utils.Locator.ByType;

/**
 * @Author:Andrew
 * @Description:读取元素xml工具类
 * @Date 2017/9/8 0008
 */
public class XmlReadUtil {
    public HashMap<String,Locator> readXMLDocument(String path,String pageName){
        Logger log= LogManager.getLogger(this.getClass().getName());
        log.info("----------开始解析对象库-----------");
        log.info("----------开始读取"+pageName+"信息");
        HashMap<String,Locator> locatorHashMap=new HashMap<String, Locator>();
        locatorHashMap.clear();
        try{
            File file=new File(path);
            if(!file.exists()){
                throw new IOException("找不到"+path+"文件");
            }
            SAXReader reader=new SAXReader();
            Document document=reader.read(file);
            Element root=document.getRootElement();
            for(Iterator<?> i = root.elementIterator();i.hasNext();){
                Element page=(Element)i.next();
                if(page.attribute(0).getValue().equalsIgnoreCase(pageName)){
                    for(Iterator<?>l=page.elementIterator();l.hasNext();){
                        String type=null;
                        String value=null;
                        String desc=null;
                        String frame=null;
                        String locatorName=null;
                        Element locator=(Element)l.next();
                        locatorName=locator.getText();
                        for(Iterator<?>j=locator.attributeIterator();j.hasNext();){
                            Attribute attribute=(Attribute)j.next();
                            if (attribute.getName().equals("type"))
                            {
                                type = attribute.getValue();
                                //log.info("读取定位方式： " + type);
                            }else if (attribute.getName().equals("value"))
                            {
                                value = attribute.getValue();
                                //log.info("读取定位内容：" + value);
                            }else if(attribute.getName().equals("desc"))
                            {
                                desc=attribute.getValue();
                            }else if(attribute.getName().equals("frame"))
                            {
                                frame=attribute.getValue();
                            }
                        }
                        Locator temp = new Locator(value.trim(), getByType(type),desc.trim(),frame.trim(),locatorName.trim());
                        locatorHashMap.put(locatorName.trim(),temp);
                    }
                }
            }
        }catch(Exception e){
            log.error("读取元素失败");
            e.printStackTrace();
        }
        return locatorHashMap;
    }
    /**
     * By.Type
     * */
    public static ByType getByType(String type) {
        ByType byType = ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            byType = ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            byType = ByType.id;
        } else if (type.equalsIgnoreCase("linkText")) {
            byType = ByType.linkText;
        } else if (type.equalsIgnoreCase("name")) {
            byType = ByType.name;
        } else if (type.equalsIgnoreCase("className")) {
            byType = ByType.className;
        } else if (type.equalsIgnoreCase("cssSelector")) {
            byType = ByType.cssSelector;
        } else if (type.equalsIgnoreCase("partialLinkText")) {
            byType = ByType.partialLinkText;
        } else if (type.equalsIgnoreCase("tagName")) {
            byType = ByType.tagName;
        }
        return byType;
    }
}

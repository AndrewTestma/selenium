package utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/9/8 0008
 */
public class PageObjectAutoCode {
    static String xml="Test.xml";
    static  String path="ElementXML/"+xml;
    public static void autoCode() throws Exception
    {
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("Can't find " + path);
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        //对象库xml文件根节点
        Element root = document.getRootElement();
        //遍历根节点下的第一个节点（page节点）
        for(Iterator<?> i = root.elementIterator(); i.hasNext();)
        {
            Element page=(Element)i.next();
            //获取page节点的name属性值
            String pageName=page.attribute(0).getValue();
            System.out.println(pageName);
            //将pageName存储为数组
            String[] pageNameArray=pageName.split("\\.");
            System.out.println(pageNameArray);
            System.out.println(pageNameArray[0]);
            //获取要写入的page所属的类名
            String pageClassName=pageNameArray[1].toString();
            //获取对象库包名
            //String packageName=pageNameArray[0].toString();
            //--自动编写对象库代码（XXPage.java）开始--
            StringBuffer sb=new StringBuffer("package pageObject;\n");
            sb.append("import java.io.IOException;\n");
            sb.append("import utils.BaseAction;\n");
            sb.append("import utils.Locator;\n");
            sb.append("public class "+ pageClassName+" extends BaseAction {\n");
            sb.append("//用于eclipse工程内运行查找对象库文件路径\n");
            sb.append("private String path=\"ElementXML/"+xml+"\";\n");
            //sb.append("//用户打包成jar后查找对象库文件路径\n");
            //sb.append("private InputStream pathInputStream=PageObjectAutoCode.class.getClassLoader().getResourceAsStream(\"net/hk515/pageObjectConfig/UILibrary.xml\");	\n");
            sb.append(" public   "
                    + pageClassName
                    + "() {\n");
            sb.append("//工程内读取对象库文件\n	");
            sb.append("     setXmlObjectPath(path);\n");
            sb.append("     getLocatorHashMap();");
            sb.append("\n}");
            //sb.append("\n private String path=PageObjectAutoCode.class.getClassLoader().getResource(\"net/hk515/pageObjectConfig/UILibrary.xml\").getPath();");
            //遍历Page节点下的Locator节点
            for(Iterator<?> j=page.elementIterator();j.hasNext();)
            {
                //获取locaror节点
                Element locator =(Element)j.next();
                String locatorName=locator.getTextTrim();
                if(locator.attributeCount()>3)
                {sb.append("\n/***\n"
                        + "* "+locator.attribute(2).getValue()+"\n"
                        + "* @return\n"
                        + "* @throws IOException\n"
                        + "*/\n");
                }
                else
                {
                    sb.append("\n");
                }
                sb.append("public Locator "+locatorName+"() throws IOException\n {\n");
                //sb.append("   setXmlObjectPath(path);\n");
                sb.append("   Locator locator=getLocator(\""+locatorName+"\");\n");
                sb.append("   return locator;\n }\n");
            }
            sb.append("}");
            //将自动生成的PageObject代码写入文件
            File pageObjectFile=new File("src/main/java/pageObject/"+pageClassName+".java");
            if(pageObjectFile.exists())
            {
                pageObjectFile.delete();;
            }
            try {
                FileWriter fileWriter=new FileWriter(pageObjectFile, false);
                BufferedWriter output = new BufferedWriter(fileWriter);
                output.write(sb.toString());
                output.flush();
                output.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println(sb);
        }
    }
    public static void main(String []args) throws Exception{
        PageObjectAutoCode.autoCode();
    }
}

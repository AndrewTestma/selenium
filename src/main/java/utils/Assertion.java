package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
public class Assertion extends TestBaseCase{
    public static List<Error> errorList=new ArrayList<Error>();
    public static List<String>asserInfoList=new ArrayList<String>();
    public static Integer errorIndex=0;
    public static String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date).toString();
    }
    private static Logger log= LogManager.getLogger(Assertion.class.getName());
    /**
     *
     * */
    public static void Veritytest(String  i,String  m){
        String verityStr="Asser验证:{"+"实际值"+i+",预期值"+m+"}";
        Boolean flagBoolean=i.equals(m);
        try{
            Assert.assertTrue(flagBoolean);
            AssertPassLog(i,m);
            asserInfoList.add(verityStr+":pass");
        }catch(Error e){
            errorList.add(e);
            AsserFaileLog();
            asserInfoList.add(verityStr+":failed");
        }
    }
    public static void AssertPassLog(String str1,String str2){
        log.info("【Assert验证】:"+"判断[比较]"+"{"+str1+","+str2+"}"+"是否一致[相等]");
    }
    public static void AsserFaileLog(){
        log.error("【Assert验证  failed!】");
    }
}

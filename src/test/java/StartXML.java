import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author:Andrew
 * @Description:并发testng.xml,进行分布式并发测试
 * @Date 2017/10/10 0010
 */
public class StartXML {
      public static void main(String []args){
          ThreadPoolExecutor executor=new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,
                  new ArrayBlockingQueue<Runnable>(5));
          for(int i=0;i<1;i++){
              SetupXML setupXML=new SetupXML(i);
              executor.execute(setupXML);
          }
          executor.shutdown();
      }
}
class SetupXML implements Runnable{
    int i;
    public SetupXML(int n){
        i=n;
    }
    @Override
    public void run(){
        List suites= Lists.newArrayList();
        if(i==0){
            TestNG testNG=new TestNG();
            suites.add("testng.xml");
            testNG.setTestSuites(suites);
            System.out.println("正在执行的Task  : testng.xml");
            testNG.run();
        }
    }
}

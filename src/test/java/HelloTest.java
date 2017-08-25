import org.testng.annotations.Test;
import utils.Assertion;
import utils.TestBaseCase;

/**
 * Created by Administrator on 2017/8/25 0025.
 */
public class HelloTest extends TestBaseCase{
    @Test
    public void test(){
        extentTest=extentReports.startTest("测试");
        String a="1";
        String b="1";
        Assertion.Veritytest(a,b);
    }
}

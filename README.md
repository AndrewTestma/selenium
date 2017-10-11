# selenium
### 创建自动化测试框架
-----
> 使用selenium webdriver testng log4j2 extentreport技术搭建自动化框架

- 内部类关系图
![images](https://github.com/AndrewTestma/selenium/blob/master/src/main/resources/Images/%E6%A1%86%E6%9E%B6%E5%86%85%E9%83%A8%E5%9B%BE.jpg)

### TestBaseCase类

> 定义TestBaseCase类,使webdriver始终统一，初始化浏览器，设置ExtentReport相关配置以及执行Test方法前的前置准备
   
   
  - 测试框架使用的Testng,所以使用@BeforeSuite来初始化extentReport的文件输出位置以及系统显示信息
   
    ```
    extentReports=new ExtentReports(reportLocation,true);
    extentReports.addSystemInfo("Author","Andrew");
    ```
    
    reportLocation是测试报告的地址，在这里用的根目录,reportLocation=ExetentReport.Html,true 表示每次都重写报告。
   
  - 使用@BeforeClass来定义需要打开的浏览器驱动，在这里用log4j输出到信息到控制台来查看测试进程
  
  
  
-----
> 在进行并发操作时，线程之间数据跑串！！！

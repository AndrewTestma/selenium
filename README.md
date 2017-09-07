# selenium
### 创建自动化测试框架
-----
> 使用selenium webdriver testng log4j2 extentreport技术搭建自动化框架

- 自定义##Testng监听器##:TestListenerAdapter
  继承TestListenerAdapter类，重写onTestStart，onTestFailure，onTestSkipped，onTestSuccess，onFinish方法
  ```
    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        logger.info("测试用例:"+tr.getName()+"---start");
        extent=TestBaseCase.getextent();
        //extentTest=extent.startTest(tr.getMethod().getDescription());
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        logger.info("【" +tr.getMethod().getDescription() + " Failure】");
        extentTest.log(LogStatus.FAIL, tr.getThrowable());
        extent.endTest(extentTest);

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        logger.info("【" + tr.getMethod().getDescription() + " Skipped】");
        extentTest.log(LogStatus.SKIP, "SKIP");
        extent.endTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        logger.info("【" + tr.getMethod().getDescription() + " Success】");
        //logger.info("参数:"+tr.getParameters()[0]);
        extent.endTest(extentTest);
        extent.flush();
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }
  ```

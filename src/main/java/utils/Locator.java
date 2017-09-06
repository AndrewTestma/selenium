package utils;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/8/28 0028
 */
public class Locator {
    private String element;//查找元素内容-->value
    private int waitSec;//等待时间-->timeout
    private String locatorName;//locator名称
    private String desc;//备注-->desc
    private ByType byType;//查找方式-->type
    public enum ByType{
        xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
    }
    public Locator() {
    }
    public Locator(String element) {
        this.element = element;
        this.waitSec = 3;
        this.byType = ByType.xpath;
    }
    public Locator(String element, int waitSec) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = ByType.xpath;
    }
    public Locator(String element, int waitSec, ByType byType) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = byType;
    }
    public Locator(String element, int waitSec, ByType byType,String desc,String locatorName) {
        this.waitSec = waitSec;
        this.element = element;
        this.byType = byType;
        this.desc=desc;
        this.locatorName=locatorName;
    }
    public String getElement() {
        return element;
    }
    public int getWaitSec() {
        return waitSec;
    }
    public ByType getBy() {
        return byType;
    }
    public String getdesc(){
        return desc;
    }
    public String getLocalorName()
    {
        return locatorName;
    }
    public void setBy(ByType byType) {
        this.byType = byType;
    }
}

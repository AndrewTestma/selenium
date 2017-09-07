package utils;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/8/28 0028
 */
public class Locator {
    private String element;//查找元素内容-->value
    private String locatorName;//locator名称
    private String desc;//备注-->desc
    private ByType byType;//查找方式-->type
    private String frame;//frame-->frame
    public enum ByType{
        xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
    }
    public Locator() {
    }
    public Locator(String element) {
        this.element = element;
        this.byType = ByType.xpath;
    }

    public Locator(String element, ByType byType) {
        this.element = element;
        this.byType = byType;
    }
    public Locator(String element, ByType byType,String desc,String frame,String locatorName) {
        this.element = element;
        this.byType = byType;
        this.desc=desc;
        this.locatorName=locatorName;
        this.frame=frame;
    }
    public String getElement() {
        return element;
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
    public String getframe(){
        return frame;
    }
    public void setBy(ByType byType) {
        this.byType = byType;
    }
}

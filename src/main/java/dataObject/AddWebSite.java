package dataObject;

/**
 * @Author:Andrew
 * @Description:添加网站-测试数据对象
 * @Date 2017/9/13 0013
 */
public class AddWebSite {
    private String webSiteName;//网站名称
    private String webSiteAccount;//网站账号
    private String webSiteType;//网站类型
    private String virtualDomain;//虚拟域名
    private String manageAccount;//管理账号
    private String selectUserAccount;//选择管理员账号
    private String addUserAccount;//添加管理员账号
    private String addUserPassword;//添加管理员密码
    private String remarks;//备注

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }

    public String getWebSiteAccount() {
        return webSiteAccount;
    }

    public void setWebSiteAccount(String webSiteAccount) {
        this.webSiteAccount = webSiteAccount;
    }

    public String getWebSiteType() {
        return webSiteType;
    }

    public void setWebSiteType(String webSiteType) {
        this.webSiteType = webSiteType;
    }

    public String getVirtualDomain() {
        return virtualDomain;
    }

    public void setVirtualDomain(String virtualDomain) {
        this.virtualDomain = virtualDomain;
    }

    public String getManageAccount() {
        return manageAccount;
    }

    public void setManageAccount(String manageAccount) {
        this.manageAccount = manageAccount;
    }

    public String getSelectUserAccount() {
        return selectUserAccount;
    }

    public void setSelectUserAccount(String selectUserAccount) {
        this.selectUserAccount = selectUserAccount;
    }

    public String getAddUserAccount() {
        return addUserAccount;
    }

    public void setAddUserAccount(String addUserAccount) {
        this.addUserAccount = addUserAccount;
    }

    public String getAddUserPassword() {
        return addUserPassword;
    }

    public void setAddUserPassword(String addUserPassword) {
        this.addUserPassword = addUserPassword;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String addWebSiteStr(){
        String str="{"+getWebSiteName()+"} {"+getWebSiteAccount()+"} {"+getWebSiteType()
                +"} {"+getVirtualDomain()+"} {"+getManageAccount()+"} {"+getSelectUserAccount()
                +"} {"+getAddUserAccount()+"} {"+getAddUserPassword()+"} {"+getRemarks()+"}";
        return  str;
    }
    public AddWebSite(String webSiteName,String webSiteAccount,String webSiteType,String virtualDomain,
                      String manageAccount,String selectUserAccount,String addUserAccount,String addUserPassword,String remarks){
        setWebSiteName(webSiteName);
        setWebSiteAccount(webSiteAccount);
        setWebSiteType(webSiteType);
        setVirtualDomain(virtualDomain);
        setManageAccount(manageAccount);
        setSelectUserAccount(selectUserAccount);
        setAddUserAccount(addUserAccount);
        setAddUserPassword(addUserPassword);
        setRemarks(remarks);
    }
}

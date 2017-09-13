package dataObject;

/**
 * @Author:Andrew
 * @Description:测试数据对象--登录账号
 * @Date 2017/9/13 0013
 */
public class LoginData {
    public String loginType;
    public String loginName;
    public String loginPassword;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    public String loginStr(){
        String str="{"+getLoginType()+"} {"+getLoginName()+"} {"+getLoginPassword()+"}";
        return str;
    }
    public LoginData(String loginType,String loginName,String loginPassword){
        setLoginType(loginType);
        setLoginName(loginName);
        setLoginPassword(loginPassword);
    }
}

package com.lgfei.demo.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.lgfei.demo.ssh.pojo.Manager;
import com.lgfei.demo.ssh.services.IManagerService;
import com.lgfei.demo.ssh.utils.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport implements RequestAware,SessionAware
{
    private static final long serialVersionUID = 3272659182936141776L;
   
    private Map<String, Object> request;
    private Map<String, Object> session;
    
    @Override
    public void setRequest(Map<String, Object> req) {
        this.request = req;
    }
    @Override
    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }
    
    private IManagerService mgService;
    
    public IManagerService getMgrService()
    {
        return mgService;
    }
    
    public void setMgService(IManagerService mgService)
    {
        this.mgService = mgService;
    }
    
    private Manager manager;
    
    private String verificationCode;
    
    public Manager getManager()
    {
        return manager;
    }
    
    public void setManager(Manager manager)
    {
        this.manager = manager;
    }
    
    public String getVerificationCode()
    {
        return verificationCode;
    }
    
    public void setVerificationCode(String verificationCode)
    {
        this.verificationCode = verificationCode;
    }
    
    private String errMsg;
    
    public String getErrMsg()
    {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }
    
    public String login()
    {
        session.remove("errMsg");
        boolean isPass = true;
        Manager vo = mgService.findOne(manager.getNo());
        if (null == vo)
        {
            errMsg = "该管理员不存在";
            isPass = false;
        }
        else
        {
            String dbMgPwd = vo.getPwd();
            if (!StringUtil.isEqual(dbMgPwd, manager.getPwd()))
            {
                errMsg = "密码错误";
                isPass = false;
            }
            String vfCode = (String)session.get("verificationCode");
            if (!StringUtil.isEqual(vfCode, verificationCode))
            {
                errMsg = "验证码错误";
                isPass = false;
            }
        }
        if (!isPass)
        {
            session.put("errMsg", errMsg);
            return ERROR;
        }
        else
        {
            session.put("loginMgName", vo.getName());
            request.put("page", "welcome");
            return SUCCESS;
        }
    }
    
    public String logout()
    {
        session.clear();
        return "logout";
    }
}

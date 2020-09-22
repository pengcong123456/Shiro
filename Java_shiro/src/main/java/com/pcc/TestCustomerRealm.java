package com.pcc;

import com.pcc.relam.CustomerRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @author : 彭聪
 * @date : 2020-09-19 09:29
 **/
public class TestCustomerRealm {
    public static void main(String[] args) {
        //1.创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //2.给安全管理器设置relam
        securityManager.setRealm(new CustomerRealm());
        //3.给全局工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4关键对象Subject主题
        Subject subject = SecurityUtils.getSubject();
        //5创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("pc", "123");
        System.out.println("认证前状态：" + subject.isAuthenticated());
        try {
            //用户认证
            subject.login(token);
            System.out.println("认证后状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败，用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败，密码错误");
        }
    }
}


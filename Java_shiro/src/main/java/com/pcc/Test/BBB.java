package com.pcc.Test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 彭聪
 * @date : 2020-09-19 11:32
 **/
public class BBB {
    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        AAA realm = new AAA();

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(hashedCredentialsMatcher);

        defaultSecurityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("pcc", "123456");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("账户错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        if (subject.isAuthenticated()) {
            System.out.println("权限" + subject.hasRole("admin"));

            List<String> alist = new ArrayList<>();
            alist.add("admin");
            alist.add("user");

            System.out.println("权限" + subject.hasAllRoles(alist));

            boolean[] booleans = subject.hasRoles(alist);
            for (boolean a : booleans) {
                System.out.println("权限" + a);
            }

            System.out.println("权限"+subject.isPermitted("user:*"));
            System.out.println("权限"+subject.isPermitted("product:delete:01"));

            System.out.println("权限"+subject.isPermittedAll("user:*","product:delete:01"));
            boolean[] booleans1 =subject.isPermitted("user:*","product:delete:01");
            for (boolean a :booleans1){
                System.out.println("权限"+a);
            }
        }
    }
}

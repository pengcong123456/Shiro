package com.pcc.relam;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author : 彭聪
 * @date : 2020-09-19 09:24
 * 使用自定义realm实现
 **/
public class CustomerRealm extends AuthorizingRealm {
    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在token中获取用户名
        String principal = (String) token.getPrincipal();
       //调用mybatis查询数据库
        if ("pc".equals(principal)) {
            //参数一：返回数据库中正确的用户名，参数二：返回数据库中的正确的的密码，参数三：提供当前realm的名字
            return new SimpleAuthenticationInfo("pc", "123", this.getName());

        }
        return null;
    }
}

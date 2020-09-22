package com.pcc.Test;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author : 彭聪
 * @date : 2020-09-19 11:27
 **/
public class AAA extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("身份信息："+principal);

        SimpleAuthorizationInfo simpleAuthorizationInfo =new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");

        simpleAuthorizationInfo.addStringPermission("user:*:*");
        simpleAuthorizationInfo.addStringPermission("product:creat:*");

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        String principal= (String) Token.getPrincipal();
        if("pcc".equals(principal)){

            return new SimpleAuthenticationInfo(principal,
                    "da12b5fd10ae43b460e66f45f2a0ce3c",
                    ByteSource.Util.bytes("adc*kill"),
                    this.getName());
        }
        return null;
    }
}

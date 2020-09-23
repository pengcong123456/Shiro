package com.pc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.pc.shiro.cache.RedisCacheManager;
import com.pc.shiro.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 彭聪
 * @date : 2020-09-19 16:15
 **/
@Configuration
public class ShiroConfig {
    //加入方言，前端页面标签生效
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
    //1创建shirofilter过滤器
    @Bean
    public ShiroFilterFactoryBean getShShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        //配置系统公共资源
        Map<String, String> map = new HashMap<String, String>();
        map.put("/login.html", "anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/user/getImage", "anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/user/register", "anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/user/registerview", "anon");//anon 设置为公共资源  放行资源放在下面
        map.put("/user/login", "anon");//anon 设置为公共资源  放行资源放在下面

        map.put("/**", "authc");//authc 请求这个资源需要认证和授权

        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/user/loginview");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultwebSecurityManager = new DefaultWebSecurityManager();
        defaultwebSecurityManager.setRealm(realm);
        return defaultwebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm() {

        CustomerRealm customerRealm = new CustomerRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置匹配器算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //hash散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        //realm设置匹配器
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        //开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());//ehCacheManager
        customerRealm.setCachingEnabled(true);//开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);//认证认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");//去别名
        customerRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;


    }
}

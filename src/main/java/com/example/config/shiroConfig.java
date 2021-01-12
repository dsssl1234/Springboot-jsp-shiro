package com.example.config;

import com.example.config.realms.customerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {

    //1、创建ShiroFilterFactoryBean, 负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源
        //添加shiro内置过滤器
        Map filterMap = new LinkedHashMap<>();
        //授权，正常情况下，没有授权会跳转到未授权的页面,然后在Realm中的授权中获取用户权限
        //thymeleaf不能直接写试图(index.html,需要通过控制层转换)，
        filterMap.put("/user/**","anon");//请求不被拦截
        filterMap.put("/register.jsp","anon");//请求不被拦截
        filterMap.put("/**","authc");//请求被拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");//被拦截后跳转到登录页面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");//未授权，跳转到该页面
        //配置系统公共资源
        return shiroFilterFactoryBean;
    }

    //2、创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3、创建自定义realm
    @Bean
    public Realm getRealm(){
        customerRealm userRealm = new customerRealm();
        //设置密码的验证规则改为MD5
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //使用的算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }
}

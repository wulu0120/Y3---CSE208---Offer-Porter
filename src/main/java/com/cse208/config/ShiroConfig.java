package com.cse208.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加内置过滤器
        Map<String,String> filterMap=new LinkedHashMap<>();
        filterMap.put("/static/*","anon"); //permitAll --> static sources
        filterMap.put("/publicWeb/*","anon"); //只能登陆后访问

        filterMap.put("/dashboard","authc"); //只能登陆后访问
        //filterMap.put("/addOffer","authc"); //只能登陆后访问
        //filterMap.put("/uploadOffer","authc"); //只能登陆后访问
        filterMap.put("/userOnly/*","authc"); //只能登陆后访问

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //拦截后跳转登录页面
        shiroFilterFactoryBean.setLoginUrl("/forbidden");

        return shiroFilterFactoryBean;
    }

    @Bean                                                              //下面[方法的名字]
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userAuthority") UserAuthority userAuthority){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联自定义对象
        securityManager.setRealm(userAuthority);
        return securityManager;
    }

    //自定义对象
    @Bean
    public UserAuthority userAuthority(){
        return new UserAuthority();
    }
}

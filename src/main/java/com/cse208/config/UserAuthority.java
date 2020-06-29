package com.cse208.config;

import com.cse208.Entity.User;
import com.cse208.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthority extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证");

        //用户名密码  --连接数据库
        UsernamePasswordToken userToken= (UsernamePasswordToken) authenticationToken;
        User user=userService.getUserByName(userToken.getUsername());
        if (user==null){
            System.out.println("no");
            return null;  //抛出异常，对应LoginController中的
        }
        return new SimpleAuthenticationInfo("",user.getPwd(),"");
    }
}

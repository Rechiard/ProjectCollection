package com.config;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//自定义的UserRealm 需要继承AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了认证doGetAuthorizationInfo");

        //用户名，密码    数据库中存储
        String name = "root";
        String password = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken)authenticationToken;

        if (!userToken.getUsername().equals(name)){
            return null;    //如果return null就会抛出异常
        }

        //密码认证：shiro做
        return new SimpleAuthenticationInfo("",password,"");
    }
}

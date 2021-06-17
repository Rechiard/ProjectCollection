package com.example.frame.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.frame.shiro.util.ShiroByteSource;
import com.example.frame.util.MyStringUtils;
import com.example.system.entity.UserInfo;
import com.example.system.service.ResourceInfoService;
import com.example.system.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 认证权限验证
 *
 * Auz:权限认证
 * Auc:身份认证
 *
 * @author bobo
 * @date 2021/04/15
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    UserInfoService userInfoService;
    @Autowired
    ResourceInfoService resourceInfoService;

    public ShiroRealm(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false); // 这一行决定hex还是base641651
        setCredentialsMatcher(hashedCredentialsMatcher);
    }

     //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入权限验证中.....");
        return null;
    }

    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        System.out.println("进入登录验证.....");
        UsernamePasswordToken token = (UsernamePasswordToken)Token;
        String username = token.getUsername();
        System.out.println(username);
        List<UserInfo> list = userInfoService.list(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getName, username));

        if(list.size() !=1 || CollectionUtils.isEmpty(list)){
            throw new UnknownAccountException("用户不存在");
        }

        UserInfo user = list.get(0);
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());
        //ShiroByteSource salt = new ShiroByteSource(user.getSalt());
        System.out.println("password="+user.getPassword());
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }
}

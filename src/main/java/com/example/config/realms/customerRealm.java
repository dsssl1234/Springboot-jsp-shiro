package com.example.config.realms;

import com.example.entity.Perms;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class customerRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取身份信息
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        //根据用户名获取角色信息和权限信息
        User user = userService.findRolesByUserName(primaryPrincipal.getUserName());
        //授权信息
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                List<Perms> permsByRid = userService.findPermsByRid(role.getId());
                permsByRid.forEach(perms -> {
                    simpleAuthorizationInfo.addStringPermission(perms.getName());
                });

            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByUserName(token.getUsername());
        System.out.println(user.toString());
        if(ObjectUtils.isEmpty(user)){
            return null;
        }
        return new SimpleAuthenticationInfo(user,user.getPassWord(), ByteSource.Util.bytes(user.getSalt()),getName());

    }
}

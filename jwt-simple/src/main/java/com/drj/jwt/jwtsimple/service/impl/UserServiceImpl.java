package com.drj.jwt.jwtsimple.service.impl;

import com.drj.jwt.jwtsimple.model.UserModule;
import com.drj.jwt.jwtsimple.model.UserPermission;
import com.drj.jwt.jwtsimple.security.AdminUserDetails;
import com.drj.jwt.jwtsimple.service.UserService;
import com.drj.jwt.jwtsimple.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UmsAdminService实现类
 * @author drj
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public UserModule register(UserModule umsAdminParam) {
        UserModule umsAdmin = new UserModule();
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);

        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public void logout(String oldToken) {
        // 可结合Redis让token失效
    }

    public UserDetails loadUserByUsername(String username){
        //从数据库获取用户信息
        UserModule admin = getUserByUsername(username);
        if (admin != null) {
            // 从数据库获取用户权限集合
            List<UserPermission> permissionList = new ArrayList<>();

            return new AdminUserDetails(admin,permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 从数据库查询用户信息
     * @param username
     * @return
     */
    private UserModule getUserByUsername(String username) {
        UserModule userModule = new UserModule();
        userModule.setId(1L);
        userModule.setUsername("admin");
        userModule.setPassword("123456");
        return userModule;
    }

}

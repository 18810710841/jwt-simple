package com.drj.jwt.jwtsimple.service;

import com.drj.jwt.jwtsimple.model.UserModule;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户服务
 * @author drj
 */
public interface UserService {

    /**
     * 注册功能
     */
    UserModule register(UserModule umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     *
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 登出功能
     */
    void logout(String oldToken);

    UserDetails loadUserByUsername(String username);
}

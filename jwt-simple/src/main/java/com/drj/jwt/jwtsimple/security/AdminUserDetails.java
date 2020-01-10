package com.drj.jwt.jwtsimple.security;

import com.drj.jwt.jwtsimple.model.UserModule;
import com.drj.jwt.jwtsimple.model.UserPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * @author drj
 */
public class AdminUserDetails implements UserDetails {
    private UserModule userModule;
    private List<UserPermission> permissionList;
    public AdminUserDetails(UserModule userModule, List<UserPermission> permissionList) {
        this.userModule = userModule;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userModule.getPassword();
    }

    @Override
    public String getUsername() {
        return userModule.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userModule.getStatus().equals(1);
    }
}

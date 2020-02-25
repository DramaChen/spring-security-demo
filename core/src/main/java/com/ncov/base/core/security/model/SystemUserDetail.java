package com.ncov.base.core.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
public class SystemUserDetail implements UserDetails {

    private static final long serialVersionUID = 6568115551708367212L;
    /**
     * <pre>
     *     描述：可以对应数据库用户表里的字段
     * </pre>
     */
    private String id;
    private String username;
    private String password;
    private Boolean disabled;//账号是否启用
    private Boolean valid; //账号是否过期
    private Boolean locked;//账号是否锁定
    private Boolean smsTimeout;//短信密码是否到期

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //实际开发中数据是从相应的数据库中读取，并进行拼装成User对象，
        // 返回，示例代码进行伪造信息，便于理解。
        ///下面这行代码是用来为用户分配权限，配置类会根据权限来限制访问，产生不同结果
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return valid;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return smsTimeout;
    }

    @Override
    public boolean isEnabled() {
        return disabled;
    }
}

package com.andaily.zhishifenzi.domain.shared.security;

import com.andaily.zhishifenzi.domain.user.PlayerUser;
import com.andaily.zhishifenzi.infrastructure.DateUtils;
import com.andaily.zhishifenzi.domain.user.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Shengzhao Li
 */
public class WdcyUserDetails implements UserDetails {

    protected static final String ROLE_PREFIX = "ROLE_";

    protected User user;


    public WdcyUserDetails() {
    }

    public WdcyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + user.userRole().name()));
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    public String getDisplayName() {
        if (user instanceof PlayerUser) {
            return ((PlayerUser) user).player().fullName();
        }
        return user.username();
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
        return true;
    }

    public User user() {
        return user;
    }

    public String getLastLoginTime() {
        if (user != null && user.lastLoginTime() != null) {
            return DateUtils.toDateText(user.lastLoginTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT);
        }
        return "---";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
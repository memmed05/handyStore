package com.example.handy.configs.Security;

import com.example.handy.model.User;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private  Integer id;
    private  String username;
    private  String password;
    private  Set<? extends GrantedAuthority> grantedAuthorities;
    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
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
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

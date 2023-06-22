package com.project.manager.user.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.manager.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private  Long count;
    private String username;
    private String email;
    @JsonIgnore
    private String password;

    private static Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long count, String username, String email, String password,  Collection<? extends GrantedAuthority> authorities) {
        this.count = count;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    private static List<GrantedAuthority> getAuthorities(String... roles) {
        return Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    public static UserDetailsImpl build(User user) {
        authorities =  getAuthorities(user.getRole());

        return new UserDetailsImpl(
                user.getCount(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}

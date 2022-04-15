package com.msku.drugdosemonitoringsystem.auth;

import com.msku.drugdosemonitoringsystem.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
public class UserDetail implements UserDetails {

    UUID id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserDetail(UUID id, String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.id=id;
        this.authorities = authorities;
        this.password = password;
        this.username = username;
    }

    public static UserDetail createUserDetail(User user){
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        String role = user.getRole().getId()== 1L ? "patient" : "doctor";
        System.out.println("ROLEE"+role);
        authoritiesList.add(new SimpleGrantedAuthority(role));
        return new UserDetail(user.getId(),user.getEmail(), user.getPassword(), authoritiesList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;

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

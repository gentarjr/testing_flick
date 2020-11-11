package com.app.template.config;

import java.util.Collection;

import com.app.template.entity.ModelUsers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
    
    private Collection<GrantedAuthority> auths;
    public ModelUsers users;
    
    public UserPrincipal(ModelUsers users, Collection<GrantedAuthority> authorities){
		this.users = users;
		this.auths = authorities;
    }
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return auths;
	}

	@Override
	public String getUsername() {
		return users.getUsername();
	}

	@Override
	public String getPassword() {
		return users.getPassword();
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
		return users.getEnabled() == 1;
	}
}

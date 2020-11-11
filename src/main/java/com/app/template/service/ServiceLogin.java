package com.app.template.service;

import java.util.ArrayList;
import java.util.Collection;

import com.app.template.config.UserPrincipal;
import com.app.template.entity.ModelUsers;
import com.app.template.repository.RepoUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogin implements UserDetailsService{
    
    @Autowired
    private RepoUser repo;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		ModelUsers users = repo.findByUsername(username);
		if(users.getStatus().equals("aktif")){
			if (users == null)
				throw new UsernameNotFoundException(username);
			// GrantedAuthority
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			return new UserPrincipal(users, authorities);
		}else{
			if (users.getStatus().equals("belum aktif") || users == null)
				throw new UsernameNotFoundException(username);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			return new UserPrincipal(users, authorities);
		}
    }
    
}

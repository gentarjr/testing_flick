package com.app.template.repository;


import com.app.template.entity.ConfirmationToken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoToken extends JpaRepository<ConfirmationToken, Long> {

	public ConfirmationToken findByConfirmationToken(String confirmationToken);
    
}

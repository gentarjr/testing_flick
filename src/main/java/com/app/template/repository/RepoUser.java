package com.app.template.repository;

import com.app.template.entity.ModelUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<ModelUsers, Long>{

	public ModelUsers findByEmailIdIgnoreCase(String username);

	public ModelUsers findByUsername(String username);
    
}

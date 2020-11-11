package com.app.template.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.app.template.constand.ConstandSQL;
import com.app.template.entity.ModelUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends JpaRepository<ModelUsers, Long>{

	public ModelUsers findByEmailIdIgnoreCase(String email);

	public ModelUsers findByUsernameIgnoreCase(String username);

	public ModelUsers findByUsername(String username);

	@Query(value = ConstandSQL.detailUsers, nativeQuery = true)
	public List<ModelUsers> detailUsers(@Param("p_username") String username);

	@Modifying
	@Transactional
	@Query(value = ConstandSQL.updateAkses, nativeQuery = true)
	public void updateAkses(@Param("p_hak_akses") String hak_akses,@Param("p_status") String status, @Param("p_username") String username);
    
}

package com.bl.registration.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bl.registration.model.User;


@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
	
	
	@Query(value="Select * from user where email = ?1",nativeQuery=true)
	User findByEmail(String email);

}

package com.morningstar.bookshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.morningstar.bookshop.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	@Query(value="select * from Users where email=?1",nativeQuery = true)
	public Users findUserByEmail(String email);
	
	@Query(value="select * from Users where email=?1 and password=?2",nativeQuery = true)
	public Optional<Users> findUserByEmailAndPass(String email,String password);
}

package com.sharat.springbootstarter.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sharat.springbootstarter.info.User;

@Repository
public interface UserDetailsRepository extends CrudRepository<User, String> {

	Optional<User> findByUserName(String userName);
	
}

package com.spring.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);
}

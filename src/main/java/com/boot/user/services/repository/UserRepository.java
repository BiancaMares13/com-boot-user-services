package com.boot.user.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.user.services.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User getUserById(long id);

	public User getUserByUserName(String userName);

}

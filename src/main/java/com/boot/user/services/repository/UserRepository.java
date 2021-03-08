package com.boot.user.services.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.user.services.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User getUserById(long id);

	public User getUserByUsername(String userName);

	public List<User> getAllUsers();

}

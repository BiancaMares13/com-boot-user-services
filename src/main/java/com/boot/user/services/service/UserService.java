package com.boot.user.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.user.services.model.User;
import com.boot.user.services.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(long id) {
		return userRepository.getUserById(id);
	}

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public User getUserByUsername(String userName) {
		return userRepository.getUserByUsername(userName);
	}

}
